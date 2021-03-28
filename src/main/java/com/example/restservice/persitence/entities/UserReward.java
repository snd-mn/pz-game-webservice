package com.example.restservice.persitence.entities;

import com.example.restservice.persitence.entities.bases.TimeStampsBaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class UserReward extends TimeStampsBaseEntity implements Serializable {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @JoinColumn(name="reward_id")
    Reward reward;

    @Column(columnDefinition = "datetime default null")
    Date transferredToInventory;
}
