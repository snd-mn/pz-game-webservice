package org.projectzion.game.persitence.entities;

import javax.persistence.*;

import lombok.Data;
import org.projectzion.game.persitence.entities.rewards.Reward;

@Data
@Entity
@Table(name="node_types_rewards")
public class NodeTypeReward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name="node_type_id")
    NodeType nodeType;

    @ManyToOne
    @JoinColumn(name="reward_id")
    Reward reward;
}
