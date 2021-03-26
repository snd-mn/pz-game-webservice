package com.example.restservice.persitence.entities;

import com.example.restservice.utils.NodeType;
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
    //TODO Jackson magic?
    //    {
    //        "id": 1,
    //            "osmId": 295647998,
    //            "respawnTime": 590,
    //            "nodeType": "ORE",
    //            "gps": {
    //        "envelope": {
    //            "envelope": {
    //                "envelope": {
    //                    "envelope": {
    //                        "envelope": {
    @Column(nullable=false)
    private Point gps;

    @OneToMany(mappedBy = "user")
    private Set<UserNode> users;
}
