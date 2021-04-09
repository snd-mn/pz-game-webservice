package org.projectzion.game.utils.converter;


import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.tos.NodeTo;
import org.projectzion.game.tos.TileTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Tile2TileToConverter implements Converter<Tile, TileTo> {

    @Autowired
    Node2NodeToConverter node2NodeToConverter;

    @Override
    public TileTo convert(Tile tile) {
        TileTo tileTo = new TileTo();
        List<NodeTo> nodeTos = new ArrayList<>();
        tile.getNodes().forEach(node ->{
            nodeTos.add(node2NodeToConverter.convert(node));
        });
        tileTo.setNodeTos(nodeTos);

        return tileTo;
    }
}
