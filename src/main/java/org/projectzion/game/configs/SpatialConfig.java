package org.projectzion.game.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Data
@Configuration
@ConfigurationProperties(prefix = "pz.spatial")
public class SpatialConfig {

    public static final double EARTH_CIRCUMFERENCE = 2 * Math.PI * 6371000;
    public static final double DBL_360 = 360;

    @PostConstruct
    public void init() {
        this.initTileLon();
        this.initTileLat();
    }
    private double initTileLon() {
        if(tileLon == 0)
        {
            tileLon = 360 * tileWidthMeters / EARTH_CIRCUMFERENCE;
        }
        return tileLon;
    }

    private double initTileLat() {
        if(tileLat == 0)
        {
            tileLat = DBL_360 * tileHeightMeters / EARTH_CIRCUMFERENCE;
        }
        return tileLat;
    }

    /**
     * tile width in meters
     */
    private double tileWidthMeters;
    /**
     * tile height in meters
     */
    private double tileHeightMeters;
    //TODO needs to be called
    /**
     * tile width in degrees
     */
    public double tileLon;

    /**
     * tile height in degrees
     */
    public double tileLat;

}
