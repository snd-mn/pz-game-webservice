package com.example.restservice.factories;

import com.example.restservice.persitence.entities.Node;
import com.example.restservice.tos.OverpassTurboElement;
import com.example.restservice.tos.OverpassTurboResult;
import com.example.restservice.utils.NodeType;
import com.example.restservice.utils.SpatialUtils;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
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


    public NodeType getNodeType(){
        //PFUI BAH
        int r = random.nextInt(NodeType.TRANSFER_STATION.ordinal());
        return NodeType.valueOf(r).get();
    }


    public Node fromOverpassTurboElement(OverpassTurboElement turboElement) throws Exception {
        Node node = new Node();
        Point point = (Point) SpatialUtils.wktToGeometry("POINT (" + turboElement.getLat() + " " + turboElement.getLon() +")");
        node.setGps(point);
        node.setOsmId(turboElement.getId());
        node.setRespawnTime(getRespawnTime());
        node.setNodeType(getNodeType());
        return node;
    }
}
