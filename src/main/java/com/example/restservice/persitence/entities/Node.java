package com.example.restservice.persitence.entities;

import com.example.restservice.utils.DisplayType;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
public class Node {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long respawnTime;
    private DisplayType displayType;

    @OneToMany(mappedBy = "user")
    private Set<User> users;
}
