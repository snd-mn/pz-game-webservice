package org.projectzion.game.utils;

public class Gps {
    private double lat = 0;
    private double lon = 0;

    public Gps(){

    }

    public Gps(
            double lon,
            double lat

    ) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
