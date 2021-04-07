package org.projectzion.game.services;

import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.persitence.repositories.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TileService {

    @Autowired
    SpatialConstantsService spatialConstantsService;

    @Autowired
    TileRepository tileRepository;

    public Tile getTileFromGps(double x, double y){
        return getTileFromGps(getTileCoordinatesFromGps(x,y));
    }

    public int[] getTileCoordinatesFromGps(double x, double y){
        //move to the positive side (of life :D)
        //reuse, yeah shitty, but will be callet 3000x a sec
        x = x+180;
        y = y+90;
        int qx = (int)( x / spatialConstantsService.getTileSizeX());
        int qy = (int)( y / spatialConstantsService.getTileSizeY());
        return new int[]{qx,qy};
    }

    public Tile getTileFromGps(int[] xy){
        //futuretodo is hashing -> id -> getById faster?
        //TODO redis!!!
        return tileRepository.findByXY(xy[0],xy[1]);
    }
}
