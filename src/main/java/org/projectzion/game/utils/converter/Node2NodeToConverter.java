package org.projectzion.game.utils.converter;


import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.NodeType;
import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.tos.NodeTo;
import org.projectzion.game.tos.TileTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Node2NodeToConverter implements Converter<Node, NodeTo> {


    @Override
    public NodeTo convert(Node node) {
        NodeTo nodeTo = new NodeTo();
        nodeTo.setId(node.getId());
        nodeTo.setNodeType(node.getNodeType().getId());
        nodeTo.setLon(node.getGps().getY());
        nodeTo.setLat(node.getGps().getX());
        //TODO it needs to be clear (is there a collectedNode?)
        nodeTo.setNextAvailability(0L);

        return nodeTo;
    }
}
