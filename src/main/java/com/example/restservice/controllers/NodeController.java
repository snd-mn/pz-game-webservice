package com.example.restservice.controllers;

import com.example.restservice.persitence.entities.Node;
import com.example.restservice.persitence.entities.User;
import com.example.restservice.services.NodesService;
import com.example.restservice.services.OverpassTurboService;
import com.example.restservice.services.UserService;
import com.example.restservice.tos.OverpassTurboResult;
import com.example.restservice.utils.Constants;
import com.example.restservice.utils.Gps;
import com.example.restservice.utils.OverpassTurboNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;
import java.util.List;


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
