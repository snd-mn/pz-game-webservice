package com.example.restservice.persitence.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class NextAvailability {
    @EmbeddedId
    UserNodeKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @MapsId("nodeId")
    @JoinColumn(name="node_id")
    Node node;

    long nextAvailability;
}
