package org.projectzion.game.utils;

public enum OsmGeometryType {
    NODE("node", false),
    WAY("way",false),
    RELATION("relation",false),
    //is more a way which connects start and end, to get e.g. green fields there have to be more e.g.: tag -> "natural=wood"
    AREA("way",true);
    private final String osmName;
    private final boolean checkConnectedEnds;
    OsmGeometryType(String osmName, boolean checkConnectedEnds){
        this.osmName = osmName; this.checkConnectedEnds = checkConnectedEnds;
    }
}
