package org.projectzion.game.factories;

import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.persitence.repositories.TileRepository;
import org.projectzion.game.services.overpass.turbo.NodeCriteria;
import org.projectzion.game.services.overpass.turbo.OverpassTurboService;
import org.projectzion.game.tos.OverpassTurboElement;
import org.projectzion.game.tos.OverpassTurboResult;
import org.projectzion.game.utils.DisplayResourceType;
import org.projectzion.game.utils.OverpassTurboNodeType;
import org.projectzion.game.utils.SpatialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vividsolutions.jts.geom.Point;

import java.util.*;

@Service
public class NodeFactory {
    //TODO RANDOM STREAM
    private Random random = new Random();

    @Autowired
    OverpassTurboService overpassTurboService;

    @Autowired
    TileRepository tileRepository;

    public void createNodesForTile(Tile tile) throws Exception{
        List<NodeCriteria> criteriaNodeTypes = new ArrayList<>();

        NodeCriteria crit0 = new NodeCriteria();
        crit0.setOverpassTurboNodeType(OverpassTurboNodeType.NODE);

        Map<String, String> filters = new HashMap<>();
        filters.put("amenity","post_box");
        crit0.setFilter(filters);

        criteriaNodeTypes.add(crit0);

        OverpassTurboResult overpassTurboResult = overpassTurboService.getNodesFromBox(tile.getBboxEast(),tile.getBboxWest(),tile.getBboxSouth(),tile.getBboxNorth(), criteriaNodeTypes);

        //todo mapping magic
        Set<Node> nodes = new HashSet<>();
        tile.getNodes().addAll(nodes);

        tile.setLastFactoryRun(new Date());
        tileRepository.save(tile);
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
