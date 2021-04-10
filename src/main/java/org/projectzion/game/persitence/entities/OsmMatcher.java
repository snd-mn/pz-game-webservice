package org.projectzion.game.persitence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.attributeconverters.NodeCriteriaAttributeConverter;
import org.projectzion.game.services.overpass.turbo.NodeCriteraFilter;
import org.projectzion.game.services.overpass.turbo.NodeCriteraFilterValue;
import org.projectzion.game.services.overpass.turbo.NodeCriteria;
import org.projectzion.game.utils.OverpassTurboNodeType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="osm_matcher")
public class OsmMatcher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OverpassTurboNodeType overpassTurboNodeType;

    @ElementCollection
    @CollectionTable(name = "osm_matcher_filters",
            joinColumns = { @JoinColumn(name = "osm_matcher_id") })
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "filter_key")
    @Column(name = "filter_value")
    private Map<NodeCriteraFilter, NodeCriteraFilterValue> filter;

    @OneToMany(mappedBy = "osmMatcher")
    private Set<OsmMatcherNodeType> osmMatcherNodeTypes;

    private Double minDistance;

    private Double maxDistance;

}
