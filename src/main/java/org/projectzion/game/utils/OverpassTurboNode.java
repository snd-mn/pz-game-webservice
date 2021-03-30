package org.projectzion.game.utils;

import java.util.HashMap;
//TODO why?
public class OverpassTurboNode {

    long id = 0l;
    int flags = 0;
    Gps gps = new Gps();
    HashMap<Object,Object> attributes = new HashMap<>();

    public OverpassTurboNode(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public HashMap<Object, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<Object, Object> attributes) {
        this.attributes = attributes;
    }
}
