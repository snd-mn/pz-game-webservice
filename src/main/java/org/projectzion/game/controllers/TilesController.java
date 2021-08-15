package org.projectzion.game.controllers;

import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.TargetSystem;
import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.persitence.repositories.CollectedNodesRepository;
import org.projectzion.game.persitence.repositories.NodeRepository;
import org.projectzion.game.persitence.repositories.TargetSystemRepository;
import org.projectzion.game.scoped.request.RequestScoped;
import org.projectzion.game.services.CollectedNodeService;
import org.projectzion.game.services.MmoConnectorService;
import org.projectzion.game.services.PlayerService;
import org.projectzion.game.tos.*;
import org.projectzion.game.utils.UserPrincipal;
import org.projectzion.game.utils.converter.Tile2TileToConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
public class TilesController {
    Logger log = LoggerFactory.getLogger(TilesController.class);

    @Autowired
    RequestScoped requestScoped;

    @Autowired
    PlayerService playerService;

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    MmoConnectorService mmoConnectorService;

    @Autowired
    CollectedNodesRepository collectedNodesRepository;

    @Autowired
    CollectedNodeService collectedNodeService;

    @Autowired
    TargetSystemRepository targetSystemRepository;

    @Autowired
    Tile2TileToConverter tile2TileToConverter;

    @GetMapping("pingbody")
    public ResponseEntity<String> ping(@RequestBody String body){
        return new ResponseEntity<String>(body, HttpStatus.OK);
    }

    @GetMapping("ping")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<String>("poing", HttpStatus.OK);
    }

    @Transactional
    @PostMapping("tiles")
    public ResponseEntity<TilesResponse> postTilesFromGps(@RequestBody TilesRequest tilesRequest) throws Exception {
        TilesResponse tilesResponse = new TilesResponse();
        UserPrincipal userPrincipal = requestScoped.currentUserPrincipal(); //TODO this is fucking long... shortcut!

        List<TileTo> tileTos = new ArrayList<>();
        Set<Tile> tiles = playerService.getOrCreateNearTiles(tilesRequest.getGps());
        tile2TileToConverter.setCollectedNodes(collectedNodesRepository.findCollectedNodesByUser(userPrincipal.getUser().getId()));
        tiles.forEach(tile -> {
            tileTos.add(tile2TileToConverter.convert(tile));
        });

        tilesResponse.setTiles(tileTos);

        return new ResponseEntity<TilesResponse>(tilesResponse, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("interact")
    public ResponseEntity<InteractionResponse> interactWithNode(@RequestBody InteractionRequest interactionRequest) {
        HttpStatus status = HttpStatus.OK;
        InteractionResponse interactionResponse = new InteractionResponse();
        interactionResponse.setInfo(InteractionResponse.FINE);
        Node node = nodeRepository.findById(interactionRequest.getNode()).get();
        TargetSystem targetSystem = targetSystemRepository.findById(interactionRequest.getTargetSystem()).get();
        ///////////////////////////////////////
        // todo cheat detection

        ///////////////////////////////////////
        // can i use this node?
        // nope? skip contacting
        boolean canInteract = collectedNodeService.isUserAbleToInteractWithNode(interactionRequest.getNode());

        ///////////////////////////////////////
        // contact mmoconnector
        if(canInteract){
            collectedNodeService.interactWithNode(interactionRequest.getNode());
            Boolean isPickSend = mmoConnectorService.pick(node, targetSystem);
            if(!isPickSend){
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                interactionResponse.setInfo(InteractionResponse.FAIL);
            }
        }else{
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            interactionResponse.setInfo(InteractionResponse.FAIL);
        }

        return new ResponseEntity<InteractionResponse>(interactionResponse,status);
    }
}
