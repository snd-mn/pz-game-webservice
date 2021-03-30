package org.projectzion.game.persitence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class NodeTypeReward {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name="node_type_id")
    NodeType nodeType;

    @ManyToOne
    @JoinColumn(name="reward_id")
    Reward reward;
}
