package com.example.restservice.persitence.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class NodeReward implements Serializable {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name=" node_id")
    User user;

    @ManyToOne
    @JoinColumn(name=" reward_id")
    Reward reward;

    Date nextAvailability;

    //CARE OR DIE, YOU HAVE GODLIKE ABILITIES!
}
