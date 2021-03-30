package com.example.restservice.persitence.entities;


import com.example.restservice.utils.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique=true)
    private String nick;
    @Column(unique=true)
    private String email;
    @Column(nullable = false)
    private UserRole userRole;
    @Column(columnDefinition = "bigint not null default 1")
    private Long experience;
    @Column(nullable = false)
    private String password;

    //jackson ignore?
    @OneToMany(mappedBy = "node")
    private Set<UserNode> userNodes;

    @OneToMany(mappedBy = "user")
    private Set<UserReward> userRewards;

    @OneToMany(mappedBy = "user")
    private Set<InventoryItem> inventoryItems;


}
