package com.example.restservice.persitence.entities;

import com.example.restservice.persitence.entities.NodeTypeReward;
import com.example.restservice.persitence.entities.UserReward;
import java.math.BigDecimal;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Reward {
    @Id
    Long id;

    @OneToMany(mappedBy = "reward")
    private Set<UserReward> userRewards;

    @OneToMany(mappedBy = "reward")
    Set<NodeTypeReward> nodeTypeRewards;

    @ManyToOne
    Item item;
    BigDecimal itemAmount;

    Long experience;












}
