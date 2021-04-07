package org.projectzion.game.controllers;

import org.projectzion.game.factories.NodeFactory;
import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.scoped.request.RequestScoped;
import org.projectzion.game.services.NodesService;
import org.projectzion.game.services.SpatialConstantsService;
import org.projectzion.game.services.TileService;
import org.projectzion.game.services.overpass.turbo.OverpassTurboService;
import org.projectzion.game.tos.OverpassTurboResult;
import org.projectzion.game.utils.Constants;
import org.projectzion.game.utils.Gps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class TestNodeFactory {

    Logger logger = LoggerFactory.getLogger(TestNodeFactory.class);


    @Autowired
    OverpassTurboService overpassTurboService;

    @Autowired
    TileService tileService;

    @Autowired
    NodeFactory nodeFactory;

    @GetMapping("nodefactory/test0") //51.50887814714403, 7.464912957132908
    public Collection<Node> getNodesFromGps(@RequestBody Gps gps) throws Exception {
        Tile tile = tileService.getTileFromGps(gps.getLon(),gps.getLat());
        if(tile == null)
        {
            tile = tileService.createTileFromGps(gps.getLon(),gps.getLat());
        }
        nodeFactory.createNodesForTile(tile);

        return tile.getNodes();
    }
}
