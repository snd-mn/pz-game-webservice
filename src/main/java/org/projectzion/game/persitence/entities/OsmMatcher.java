package org.projectzion.game.persitence.entities;

import lombok.Data;
import org.projectzion.game.services.overpass.turbo.NodeCriteria;
import org.projectzion.game.utils.OverpassTurboNodeType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name="osm_matcher")
public class OsmMatcher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private NodeCriteria osmNodeCriteria;

    @OneToMany(mappedBy = "osmMatcher")
    Set<OsmMatcherNodeType> osmMatcherNodeTypes;



}
