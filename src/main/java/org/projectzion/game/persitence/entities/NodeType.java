package org.projectzion.game.persitence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.conditions.Condition;
import org.projectzion.game.utils.DisplayResourceType;

import java.util.Set;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="node_types")
public class NodeType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    Set<Node> nodes;

    @OneToMany(mappedBy = "nodeType")
    Set<NodeTypeReward> nodeTypeRewards;

    @ManyToMany
    Set<Condition> conditions;

    @OneToMany(mappedBy = "nodeType")
    Set<OsmMatcherNodeType> osmMatcherNodeTypes;

    private DisplayResourceType displayResourceType;

    private int cooldown;


}
