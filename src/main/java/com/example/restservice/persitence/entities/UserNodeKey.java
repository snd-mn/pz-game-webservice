package com.example.restservice.persitence.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserNodeKey implements Serializable {

    @Column(name="user_id")
    Long userId;

    @Column(name="node_id")
    Long nodeId;

}
