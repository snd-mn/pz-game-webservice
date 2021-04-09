package org.projectzion.game.services;

import org.projectzion.game.persitence.entities.Tile;
import org.projectzion.game.persitence.repositories.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TileService {

    @Autowired
    SpatialConstantsService spatialConstantsService;

    @Autowired
    TileRepository tileRepository;

    public Tile getTileFromGps(double x, double y){
        return getTileFromCoords(getTileCoordinatesFromGps(x,y));
    }

    public Tile createTileFromGps(double x, double y){
        return createTileFromCoords(getTileCoordinatesFromGps(x,y));
    }

    public int[] getTileCoordinatesFromGps(double x, double y){
        //move to the positive side (of life :D)
        //reuse, yeah shitty, but will be callet 3000x a sec
//        x = x+180;
//        y = y+90;
        int qx = (int)( x / spatialConstantsService.getTileSizeX());
        int qy = (int)( y / spatialConstantsService.getTileSizeY());
        return new int[]{qx,qy};
    }

    public Tile getTileFromCoords(int[] xy){
        Tile tile = tileRepository.findByXY(xy[0],xy[1]);
        return tile;
    }

    public Tile createTileFromCoords(int[] xy){
        Tile tile = new Tile();
        tile.setX(xy[0]);
        tile.setY(xy[1]);

        tile.setBboxEast(xy[0]*spatialConstantsService.getTileSizeX());
        tile.setBboxWest(xy[0]*spatialConstantsService.getTileSizeX() + spatialConstantsService.getTileSizeX());
        tile.setBboxSouth(xy[1]*spatialConstantsService.getTileSizeY());
        tile.setBboxNorth(xy[1]*spatialConstantsService.getTileSizeY() + spatialConstantsService.getTileSizeY());

        tileRepository.save(tile);
        return tile;
    }
}
