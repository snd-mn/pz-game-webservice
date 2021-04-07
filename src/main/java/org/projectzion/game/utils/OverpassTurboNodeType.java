package org.projectzion.game.utils;

import lombok.Data;
import lombok.Getter;

public enum OverpassTurboNodeType {
    NODE("node", false),
    WAY("way",false),
    RELATION("rel",false),
    NWR("nwr",false),
    NW("nw",false),
    NR("nr",false),
    WR("wr",false),
    DERIVED("derived",false),
    AREA("area",false);

    //is more a way which connects start and end, to get e.g. green fields there have to be more e.g.: tag -> "natural=wood"
    @Getter
    private final String osmName;
    @Getter
    private final boolean checkConnectedEnds;

    OverpassTurboNodeType(String osmName, boolean checkConnectedEnds){
        this.osmName = osmName; this.checkConnectedEnds = checkConnectedEnds;
    }
}
