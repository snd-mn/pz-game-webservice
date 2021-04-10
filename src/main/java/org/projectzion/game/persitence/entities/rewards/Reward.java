package org.projectzion.game.persitence.entities.rewards;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.Item;
import org.projectzion.game.persitence.entities.NodeTypeReward;

import java.math.BigDecimal;
import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="rewards")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double amount;

    @OneToMany(mappedBy = "reward")
    private Set<NodeTypeReward> nodeTypeRewards;
















}
