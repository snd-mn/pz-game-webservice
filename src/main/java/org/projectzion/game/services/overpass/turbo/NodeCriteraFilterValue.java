package org.projectzion.game.services.overpass.turbo;

public enum NodeCriteraFilterValue {
    POSTBOX("postbox"),
    BRIDGE("bridge");
    //TODO A fucking lot
    public String value;

    NodeCriteraFilterValue(String value) {
        this.value = value;
    }
}
