package org.projectzion.game.utils.converter;


import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.CollectedNode;
import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.tos.NodeTo;
import org.projectzion.game.tos.TileTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class Tile2TileToConverter implements Converter<Tile, TileTo> {

    @Getter
    @Setter
    List<CollectedNode> collectedNodes = new ArrayList<>();

    @Autowired
    Node2NodeToConverter node2NodeToConverter;

    @Override
    public TileTo convert(Tile tile) {
        TileTo tileTo = new TileTo();
        tileTo.setId(tile.getId());
        tileTo.setX(tile.getX());
        tileTo.setY(tile.getY());
        List<NodeTo> nodeTos = new ArrayList<>();
        tile.getNodes().forEach(node ->{
            nodeTos.add(node2NodeToConverter.convert(node));
            nodeTos.forEach(nodeTo -> {
                AtomicReference<Long> nextAvailability = new AtomicReference<>();

                collectedNodes.forEach(collectedNode -> {
                    nextAvailability.set(collectedNode.getNextAvailability());
                });

                if(nextAvailability.get() == null){
                    nextAvailability.set(0L);
                }

                nodeTo.setNextAvailability(nextAvailability.get());
            });
        });
        tileTo.setNodes(nodeTos);

        return tileTo;
    }
}
