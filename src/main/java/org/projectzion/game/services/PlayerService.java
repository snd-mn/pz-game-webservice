package org.projectzion.game.services;

import lombok.SneakyThrows;
import org.projectzion.game.factories.NodeFactory;
import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.utils.Gps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlayerService {

    @Autowired
    NodeService nodeService;

    @Autowired
    NodeFactory nodeFactory;

    @Autowired
    TileService tileService;

    //TODO move to tile service
    @SneakyThrows
    public Set<Tile> getOrCreateNearTiles(Gps gps){
        Set<Tile> tiles = new HashSet<>();
        int[] center = tileService.getTileCoordinatesFromGps(gps.getLon(), gps.getLat());
        //0 1 2
        //3 4 5
        //6 7 8
        List<int[]> list = new ArrayList<>(9);

        list.add(new int[]{(center[0]-1), (center[1]+1)});
        list.add(new int[]{(center[0]), (center[1]+1)});
        list.add(new int[]{(center[0]+1), (center[1]+1)});

        list.add(new int[]{(center[0]-1), (center[1])});
        list.add(new int[]{(center[0]), (center[1])});
        list.add(new int[]{(center[0]+1), (center[1])});

        list.add(new int[]{(center[0]-1), (center[1]-1)});
        list.add(new int[]{(center[0]), (center[1]-1)});
        list.add(new int[]{(center[0]+1), (center[1]-1)});

        for (int[] coords : list) {
            Tile tile = tileService.getTileFromCoords(coords);
            if (tile == null) {
                tile = tileService.createTileFromCoords(coords);
                try {
                    nodeFactory.createNodesForTile(tile);
                } catch (Exception e) {
                    throw e;
                }
            }
            tiles.add(tile);
        }

        return tiles;
    }

}
