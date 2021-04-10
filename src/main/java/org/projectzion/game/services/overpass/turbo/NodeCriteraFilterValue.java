package org.projectzion.game.services.overpass.turbo;

public enum NodeCriteraFilterValue {
    POST_BOX("post_box"),
    BRIDGE("bridge");
    //TODO A fucking lot
    public String value;

    NodeCriteraFilterValue(String value) {
        this.value = value;
    }
}
