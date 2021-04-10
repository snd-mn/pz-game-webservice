package org.projectzion.game.persitence.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.rewards.Reward;

@Getter
@Setter
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
