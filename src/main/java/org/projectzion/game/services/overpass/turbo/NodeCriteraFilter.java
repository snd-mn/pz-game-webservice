package org.projectzion.game.services.overpass.turbo;

import java.util.Set;

public enum NodeCriteraFilter {

    AMENITY("AMENITY", Set.of(NodeCriteraFilterValue.POSTBOX)),
    //TODO A fucking lot
    ;
    public String filter;
    public Set<NodeCriteraFilterValue> values;

    NodeCriteraFilter(String filter, Set<NodeCriteraFilterValue> filter1) {
        this.filter = filter;
    }
}
