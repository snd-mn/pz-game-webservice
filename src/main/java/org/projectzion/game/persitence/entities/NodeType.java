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

    @OneToMany(mappedBy="nodeType")
    Set<Node> nodes;

    @OneToMany(mappedBy = "nodeType")
    Set<NodeTypeReward> nodeTypeRewards;

    @ManyToMany
    @JoinTable(
            name = "node_type_condition",
            joinColumns = @JoinColumn(name = "node_type_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id"))
    Set<Condition> conditions;

    @OneToMany(mappedBy = "nodeType")
    Set<OsmMatcherNodeType> osmMatcherNodeTypes;

    private DisplayResourceType displayResourceType;

    private int cooldown;


}
