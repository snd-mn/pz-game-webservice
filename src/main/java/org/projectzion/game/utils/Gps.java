package org.projectzion.game.utils;

import java.math.BigDecimal;

public class Gps {
    private BigDecimal lat = BigDecimal.ZERO;
    private BigDecimal lon = BigDecimal.ZERO;

    public Gps(){

    }

    public Gps(
            BigDecimal lat,
            BigDecimal lon

    ) {
        this.lat = lat;
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }
}
