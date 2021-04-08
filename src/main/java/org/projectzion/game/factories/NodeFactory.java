package org.projectzion.game.factories;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.impl.PackedCoordinateSequence.Double;
import com.vividsolutions.jts.geom.Coordinate;
import org.projectzion.game.persitence.entities.*;
import org.projectzion.game.persitence.repositories.TileRepository;
import org.projectzion.game.services.NodeService;
import org.projectzion.game.services.OsmMatcherService;
import org.projectzion.game.services.overpass.turbo.NodeCriteraFilter;
import org.projectzion.game.services.overpass.turbo.NodeCriteraFilterValue;
import org.projectzion.game.services.overpass.turbo.NodeCriteria;
import org.projectzion.game.services.overpass.turbo.OverpassTurboService;
import org.projectzion.game.tos.OverpassTurboElement;
import org.projectzion.game.tos.OverpassTurboResult;
import org.projectzion.game.utils.DisplayResourceType;
import org.projectzion.game.utils.OverpassTurboNodeType;
import org.projectzion.game.utils.SpatialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.vividsolutions.jts.geom.Point;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.StreamSupport;

@Service
public class NodeFactory {
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
        boolean sameType = osmMatcher.getOsmNodeCriteria().getOverpassTurboNodeType().getOsmName().equals(element.getType());

        AtomicBoolean sameCriteria = new AtomicBoolean(true);
        osmMatcher.getOsmNodeCriteria().getFilter().forEach((key,value) ->{
            String valFound = element.getTags().get(key.filter);
            if(valFound != value.value)
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
        AtomicReference<OsmMatcherNodeType> osmMatcherNodeType = null;
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

        NodeCriteria crit0 = new NodeCriteria();
        crit0.setOverpassTurboNodeType(OverpassTurboNodeType.NODE);

        Map<NodeCriteraFilter, NodeCriteraFilterValue> filters = new HashMap<>();
        filters.put(NodeCriteraFilter.AMENITY,NodeCriteraFilterValue.POSTBOX);
        crit0.setFilter(filters);

        criteriaNodeTypes.add(crit0);

        OverpassTurboResult overpassTurboResult = overpassTurboService.getNodesFromBox(tile.getBboxEast(),tile.getBboxWest(),tile.getBboxSouth(),tile.getBboxNorth(), criteriaNodeTypes);
        //todo mapping magic
        Iterable<OsmMatcher> osmMatchers = osmMatcherService.getAllOsmMatchers();
        AtomicReference<List<Node>> nodes = new AtomicReference<>();
        nodes.set(new ArrayList<>());

        overpassTurboResult.getElements().forEach(element ->{
            AtomicReference<NodeType> nodeType = new AtomicReference<>();
            StreamSupport.stream(osmMatchers.spliterator(), false).takeWhile(matcher -> null == nodeType.get()).forEach(matcher -> {
                if(match(matcher,element,overpassTurboResult)){
                    nodeType.set(getNodeTypeFromMatcher(matcher));
                }
            });
            //TODO create node from nodetype
            Node node = createNode(element, nodeType.get(), tile);
            nodeService.saveNodes(nodes.get());
            nodes.get().add(node);
        });


        tile.getNodes().addAll(nodes.get());
        tile.setLastFactoryRun(new Date());
        tileRepository.save(tile);
    }

    private Node createNode(OverpassTurboElement element, NodeType nodeType, Tile tile) {
        Node node = new Node();

        //TODO no BigDecimal plz
        Coordinate[] coordinates = new Coordinate[1];
        coordinates[0].x=element.getLon().doubleValue();
        coordinates[0].y=element.getLat().doubleValue();
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

    public Long getRespawnTime(){
        //PFUI BAH
        int r = random.nextInt(600);
        return new Long(r);
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
