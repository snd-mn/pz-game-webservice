package org.projectzion.game.controllers;

import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.persitence.repositories.CollectedNodesRepository;
import org.projectzion.game.scoped.request.RequestScoped;
import org.projectzion.game.services.PlayerService;
import org.projectzion.game.tos.TilesRequest;
import org.projectzion.game.tos.TilesResponse;
import org.projectzion.game.tos.TileTo;
import org.projectzion.game.utils.UserPrincipal;
import org.projectzion.game.utils.converter.Tile2TileToConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    CollectedNodesRepository collectedNodesRepository;

    @Autowired
    Tile2TileToConverter tile2TileToConverter;

    @Transactional
    @GetMapping("tiles")
    public TilesResponse getTilesFromGps(@RequestBody TilesRequest tilesRequest) throws Exception {
        TilesResponse tilesResponse = new TilesResponse();
        UserPrincipal userPrincipal = requestScoped.currentUserPrincipal(); //TODO this is fucking long... shortcut!

        List<TileTo> tileTos = new ArrayList<>();
        Set<Tile> tiles = playerService.getOrCreateNearTiles(tilesRequest.getGps());
        tile2TileToConverter.setCollectedNodes(collectedNodesRepository.findCollectedNodesByUser(userPrincipal.getUser().getId()));
        tiles.forEach(tile -> {
            tileTos.add(tile2TileToConverter.convert(tile));
        });

        tilesResponse.setTileTos(tileTos);
        return tilesResponse;
    }
}
