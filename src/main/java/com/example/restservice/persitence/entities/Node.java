package com.example.restservice.persitence.entities;

import com.example.restservice.utils.DisplayType;
import lombok.Data;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Node {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long respawnTime;
    private DisplayType displayType;
    private Point point;

    @OneToMany(mappedBy = "user")
    private Set<UserNode> users;
}
