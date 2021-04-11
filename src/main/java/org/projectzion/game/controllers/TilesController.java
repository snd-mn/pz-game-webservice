package org.projectzion.game.controllers;

import org.projectzion.game.persitence.entities.Character;
import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.persitence.repositories.CharacterRepository;
import org.projectzion.game.scoped.request.RequestScoped;
import org.projectzion.game.services.PlayerService;
import org.projectzion.game.tos.TilesRequest;
import org.projectzion.game.tos.TilesResponse;
import org.projectzion.game.tos.TileTo;
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
    Logger logger = LoggerFactory.getLogger(TilesController.class);

    @Autowired
    RequestScoped requestScoped;

    @Autowired
    PlayerService playerService;

    //TODO service
    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    Tile2TileToConverter tile2TileToConverter;

    //TODO requesting user
    @Transactional
    @GetMapping("tiles") //51.50887814714403, 7.464912957132908
    public TilesResponse getTilesFromGps(@RequestBody TilesRequest tilesRequest) throws Exception {
        TilesResponse tilesResponse = new TilesResponse();
        //TODO check priviliges here
        requestScoped.currentUserPrincipal();
        //TODO hmm, create some
//        Character character = characterRepository.findById(tilesRequest.getCharacterId()).get();
        Character character = new Character();

        //TODO create a query for this!
        Set<Tile> tiles = playerService.getOrCreateNearTiles(tilesRequest.getGps());
        List<TileTo> tileTos = new ArrayList<>();
        tiles.forEach(tile -> {
            tileTos.add(tile2TileToConverter.convert(tile));
        });

        tilesResponse.setTileTos(tileTos);
        return tilesResponse;
    }
}
