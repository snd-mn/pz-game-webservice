package org.projectzion.game.persitence.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="osm_matcher_node_type")
public class OsmMatcherNodeType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name="osm_matcher_id")
    private OsmMatcher osmMatcher;

    @ManyToOne
    @JoinColumn(name="node_type_id")
    private NodeType nodeType;

    //chance to proc, make some random shit and compare the results to place the right nodetype
    private int chance;
}
