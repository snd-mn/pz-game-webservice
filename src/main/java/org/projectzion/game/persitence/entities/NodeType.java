package org.projectzion.game.persitence.entities;

import org.projectzion.game.persitence.entities.bases.LocalizedBaseEntity;
import org.projectzion.game.utils.ResourceType;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class NodeType extends LocalizedBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    Set<Node> nodes;

    @OneToMany(mappedBy = "nodeType")
    Set<NodeTypeReward> nodeTypeRewards;

    private ResourceType resourceType;

    private Long cooldown;
    //TODO maybe point / area, useable at all, collectable, something i dont know yet


}
