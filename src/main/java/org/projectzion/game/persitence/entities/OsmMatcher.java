package org.projectzion.game.persitence.entities;

import lombok.Data;
import org.projectzion.game.utils.OverpassTurboNodeType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name="osm_matcher")
public class OsmMatcher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "osmMatcher")
    Set<OsmMatcherNodeType> osmMatcherNodeTypes;

    private BigDecimal minDistance;

    private BigDecimal maxDistance;

    private OverpassTurboNodeType map2OverpassTurboNodeType;

    private Long minCooldown;

    private Long maxCooldown;

    private String map2osmTag;

}
