package com.example.restservice.persitence.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Reward {
    @Id
    Long id;

    @OneToMany(mappedBy = "user")
    private Set<UserNode> users;



}
