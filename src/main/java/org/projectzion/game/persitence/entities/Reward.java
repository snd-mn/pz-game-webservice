package org.projectzion.game.persitence.entities;

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

    @ManyToOne
    Item item;
    BigDecimal itemAmount;

    Long experience;












}
