package com.example.restservice.persitence.entities;


import com.example.restservice.utils.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique=true)
    private String nick;
    @Column(unique=true)
    private String email;
    private UserRole userRole;
    private Long experience;

    //jackson ignore?
    @OneToMany(mappedBy = "node")
    private Set<UserNode> userNodes;

    @OneToMany(mappedBy = "user")
    private Set<UserReward> userRewards;

    @OneToMany(mappedBy = "user")
    private Set<InventoryItem> inventoryItems;
}
