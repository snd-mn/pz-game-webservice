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

    //jackson ignore?
    @OneToMany(mappedBy = "node")
    private Set<UserNode> nodes;

}
