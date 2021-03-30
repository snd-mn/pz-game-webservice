package org.projectzion.game.controllers;

import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.services.NodesService;
import org.projectzion.game.services.OverpassTurboService;
import org.projectzion.game.tos.OverpassTurboResult;
import org.projectzion.game.utils.Constants;
import org.projectzion.game.utils.Gps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class NodeController {

    @Autowired
    NodesService nodesService;

    @Autowired
    OverpassTurboService overpassTurboService;

    //TODO requesting user
    @GetMapping("node/get") //51.50887814714403, 7.464912957132908
    public Collection<Node> getNodesFromGps(@RequestBody Gps gps) throws Exception {
        OverpassTurboResult turboResult = overpassTurboService.getNodesByGps(OverpassTurboService.query_postbox, gps, Constants.OSM_DEFAULT_SCAN_RADIUS);
        Collection<Node> nodes = nodesService.createNodesFromOverpassTurboResult(turboResult);
        //TODO filter nodes by availability

        return nodes;
    }
}
