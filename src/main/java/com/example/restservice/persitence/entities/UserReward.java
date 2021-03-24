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
public class UserReward implements Serializable {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name=" user_id")
    User user;

    @ManyToOne
    @JoinColumn(name=" reward_id")
    Node node;

    Date usedAt;
}
