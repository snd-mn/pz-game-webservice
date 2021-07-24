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
        nodeTo.setConditionFlags((byte) 0);
        nodeTo.setDisplayType((byte) node.getNodeType().getDisplayResourceType().ordinal());
        nodeTo.setLon(node.getGps().getY());
        nodeTo.setLat(node.getGps().getX());
        nodeTo.setNextRespawn(0);//TODO CollectedNodes
        nodeTo.setCooldown(node.getNodeType().getCooldown());

        HashMap<Long, Double> map = new HashMap<>();
        node.getNodeType().getNodeTypeRewards().forEach(nodeTypeReward -> {
            map.put(nodeTypeReward.getReward().getId(),nodeTypeReward.getReward().getAmount());
        });
        nodeTo.setRewardIdToAmount(map);

        return nodeTo;
    }
}
