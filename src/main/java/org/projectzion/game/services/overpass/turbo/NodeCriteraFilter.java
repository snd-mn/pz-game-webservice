package org.projectzion.game.services.overpass.turbo;

import java.util.Set;

public enum NodeCriteraFilter {

    AMENITY("amenity", Set.of(NodeCriteraFilterValue.POST_BOX)),
    //TODO A fucking lot
    ;
    public String filter;
    public Set<NodeCriteraFilterValue> values;

    NodeCriteraFilter(String filter, Set<NodeCriteraFilterValue> filter1) {
        this.filter = filter;
    }
}
