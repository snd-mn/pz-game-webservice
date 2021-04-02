package org.projectzion.game.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class SpatialConstantsService {

    private double tileSizeX;
    private double tileSizeY;

    public double getTileSizeX() {
        return tileSizeX;
    }

    /**
     * never ever use after Setups are done, please :)
     * @param tileSizeX
     */
    public void setTileSizeX(double tileSizeX) {
        this.tileSizeX = tileSizeX;
    }

    public double getTileSizeY() {
        return tileSizeY;
    }

    /**
     * never ever use after Setups are done, please :)
     * @param tileSizeY
     */
    public void setTileSizeY(double tileSizeY) {
        this.tileSizeY = tileSizeY;
    }
}
