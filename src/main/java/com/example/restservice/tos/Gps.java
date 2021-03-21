package com.example.restservice.tos;

import java.math.BigDecimal;

public class Gps {
    private BigDecimal lon = BigDecimal.ZERO;
    private BigDecimal lat = BigDecimal.ZERO;

    public Gps(){

    }

    public Gps(
            BigDecimal lon,
            BigDecimal lat
    ) {
        this.lon = lon;
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }
}
