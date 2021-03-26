package com.example.restservice.persitence.entities;

import com.example.restservice.utils.NodeType;
import com.example.restservice.utils.json.serializer.PointSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true, nullable=true)
    private Long osmId;
    private Long respawnTime;
    private NodeType nodeType;

    @Column(nullable=false)
    @JsonSerialize(using = PointSerializer.class)
    private Point gps;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserNode> users;
}
