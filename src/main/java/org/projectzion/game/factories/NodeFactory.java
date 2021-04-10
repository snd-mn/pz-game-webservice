package org.projectzion.game.factories;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.impl.PackedCoordinateSequence.Double;
import com.vividsolutions.jts.geom.Coordinate;
import org.projectzion.game.persitence.entities.*;
import org.projectzion.game.persitence.repositories.TileRepository;
import org.projectzion.game.services.NodeService;
import org.projectzion.game.services.OsmMatcherService;
import org.projectzion.game.services.overpass.turbo.NodeCriteria;
import org.projectzion.game.services.overpass.turbo.OverpassTurboService;
import org.projectzion.game.tos.OverpassTurboElement;
import org.projectzion.game.tos.OverpassTurboResult;
import org.projectzion.game.utils.DisplayResourceType;
import org.projectzion.game.utils.OverpassTurboNodeType;
import org.projectzion.game.utils.SpatialUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.vividsolutions.jts.geom.Point;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class NodeFactory {
    Logger logger = LoggerFactory.getLogger(NodeFactory.class);

    //TODO RANDOM STREAM
    private Random random = new Random();

    @Autowired
    OverpassTurboService overpassTurboService;

    @Autowired //TODO use Service instead
    TileRepository tileRepository;

    @Autowired
    OsmMatcherService osmMatcherService;

    @Autowired
    NodeService nodeService;

    @Bean
    @ApplicationScope
    public GeometryFactory getGeometryFactory() {
        return new GeometryFactory();
    }


    public boolean match(OsmMatcher osmMatcher, OverpassTurboElement element, OverpassTurboResult result)
    {
        boolean sameType = osmMatcher.getOverpassTurboNodeType().getOsmName().equals(element.getType());

        AtomicBoolean sameCriteria = new AtomicBoolean(true);
        osmMatcher.getFilter().forEach((key, value) ->{
            String valFound = element.getTags().get(key.filter);
            if(!valFound.equals(value.value))
            {
                sameCriteria.set(false);
                //TODO how to break here?
            }
        });

        //TODO way relationship stuff

        return sameType && sameCriteria.get();
    }

    public NodeType getNodeTypeFromMatcher(OsmMatcher matcher){
        NodeType nodeType = null;
        AtomicReference<OsmMatcherNodeType> osmMatcherNodeType = new AtomicReference<>();
        int highestRoll = 0;
        matcher.getOsmMatcherNodeTypes().forEach(mapping ->{
            //TODO random stuff
            if(mapping.getChance() > highestRoll)
            {
                osmMatcherNodeType.set(mapping);
            }
        });

        return osmMatcherNodeType.get().getNodeType();
    }

    @Transactional
    public void createNodesForTile(Tile tile) throws Exception{
        List<NodeCriteria> criteriaNodeTypes = new ArrayList<>();

        osmMatcherService.getAllOsmMatchers().forEach(matcher ->{
            //TODO create a table for node critera replace that Map for osmMatcher
            NodeCriteria crit0 = new NodeCriteria();
            crit0.setOverpassTurboNodeType(OverpassTurboNodeType.NODE);
            crit0.setFilter(matcher.getFilter());
            criteriaNodeTypes.add(crit0);
        });

        OverpassTurboResult overpassTurboResult = overpassTurboService.getNodesFromBox(tile.getBboxEast(),tile.getBboxWest(),tile.getBboxSouth(),tile.getBboxNorth(), criteriaNodeTypes);
        //todo mapping magic
        Iterable<OsmMatcher> osmMatchers = osmMatcherService.getAllOsmMatchers();
        AtomicReference<List<Node>> nodes = new AtomicReference<>();
        nodes.set(new ArrayList<>());

        for (OverpassTurboElement element : overpassTurboResult.getElements()) {
            AtomicReference<NodeType> nodeType = new AtomicReference<>();
            for (OsmMatcher osmMatcher : osmMatchers) {
                if(match(osmMatcher,element,overpassTurboResult)){
                    nodeType.set(getNodeTypeFromMatcher(osmMatcher));
                    break;
                }
            }
            if(nodeType.get()!= null) {
                Node node = buildNode(element, nodeType.get(), tile);
                nodes.get().add(node);
            }else{
                logger.info("no osmMatcher for OverpassTurboElement, may check your configuration: " + element.toString());
            }
        }
        //save also sets the id
        nodeService.saveNodes(nodes.get());

        if(tile.getNodes() == null)
        {
            tile.setNodes(new HashSet<>());
        }
        tile.getNodes().addAll(nodes.get());
        tile.setLastFactoryRun(new Date());
        tileRepository.save(tile);
    }

    private Node buildNode(OverpassTurboElement element, NodeType nodeType, Tile tile) {
        Node node = new Node();

        //TODO no BigDecimal plz
        Coordinate[] coordinates = new Coordinate[1];
        coordinates[0] = new Coordinate(element.getLon(),element.getLat());
        Double cordinateSequence = new Double(coordinates,2);
        Point point = new Point(cordinateSequence,getGeometryFactory());


        node.setNodeType(nodeType);
        node.setGps(point);
        node.setOsmId(element.getId());
        node.setRespawnTime(nodeType.getCooldown());
        node.setTile(tile);

        return node;
    }

    //TODO map OverpassTurboNodeAttributes to NodeType
    public Collection<Node> fromOverpassTurboResult(List<OverpassTurboElement> turboElements) throws Exception{
        Collection<Node> nodes = new ArrayList<>();
        for (OverpassTurboElement element : turboElements) {
            nodes.add(fromOverpassTurboElement(element));
        }
        return nodes;
    }

    public int getRespawnTime(){
        //PFUI BAH
        int r = random.nextInt(600);
        return r;
    }

    public DisplayResourceType getNodeType(){
        //PFUI BAH
        int r = random.nextInt(DisplayResourceType.TRANSFER_STATION.ordinal());
        return DisplayResourceType.valueOf(r).get();
    }

    public Node fromOverpassTurboElement(OverpassTurboElement turboElement) throws Exception {
        Node node = new Node();
        Point point = (Point) SpatialUtils.wktToGeometry("POINT (" + turboElement.getLat() + " " + turboElement.getLon() +")");
        node.setGps(point);
        node.setOsmId(turboElement.getId());
        node.setRespawnTime(getRespawnTime());
//        node.setNodeType(getNodeType());
        return node;
    }
}
