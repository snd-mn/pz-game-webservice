package org.projectzion.game.services.overpass.turbo;

import lombok.Data;
import org.projectzion.game.utils.OverpassTurboNodeType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Map;

//TODO test serialize n deserialize

@Data
public class NodeCriteria implements Serializable {
    @Enumerated(EnumType.STRING)
    OverpassTurboNodeType overpassTurboNodeType;

    Map<NodeCriteraFilter, NodeCriteraFilterValue> filter;
}
