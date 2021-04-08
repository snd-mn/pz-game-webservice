package org.projectzion.game.services.overpass.turbo;

import lombok.Data;
import org.projectzion.game.utils.OverpassTurboNodeType;

import java.io.Serializable;
import java.util.Map;

//TODO test serialize n deserialize
@Data
public class NodeCriteria implements Serializable {
    OverpassTurboNodeType overpassTurboNodeType;
    Map<NodeCriteraFilter, NodeCriteraFilterValue> filter;
}
