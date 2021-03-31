package org.projectzion.game.persitence.entities;

import org.projectzion.game.persitence.entities.conditions.Condition;
import org.projectzion.game.utils.ResourceType;

import java.util.Set;
import javax.persistence.*;

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

    private ResourceType resourceType;

    private Long cooldown;
    //TODO maybe point / area, useable at all, collectable, something i dont know yet


}
