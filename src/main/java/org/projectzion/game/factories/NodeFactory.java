package org.projectzion.game.factories;

import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.tos.OverpassTurboElement;
import org.projectzion.game.utils.DisplayResourceType;
import org.projectzion.game.utils.SpatialUtils;
import org.springframework.stereotype.Service;
import com.vividsolutions.jts.geom.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class NodeFactory {
    //TODO RANDOM STREAM
    private Random random = new Random();
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
