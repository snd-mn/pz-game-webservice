package org.projectzion.game.services.overpass.turbo;

import lombok.Data;
import org.projectzion.game.utils.OverpassTurboNodeType;

import java.util.Map;

@Data
public class NodeCriteria {
    OverpassTurboNodeType overpassTurboNodeType;
    Map<String,String> filter;
}
