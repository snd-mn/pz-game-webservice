package org.projectzion.game.persitence.entities.rewards;

import org.projectzion.game.persitence.entities.Item;
import org.projectzion.game.persitence.entities.NodeTypeReward;

import java.math.BigDecimal;
import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name="rewards")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "reward")
    private Set<NodeTypeReward> nodeTypeRewards;
















}
