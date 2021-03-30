package com.example.restservice.persitence.entities;

import com.example.restservice.persitence.entities.security.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class UserNode implements Serializable {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name=" user_id")
    User user;

    @ManyToOne
    @JoinColumn(name=" node_id")
    Node node;

    Long nextAvailability;
}
