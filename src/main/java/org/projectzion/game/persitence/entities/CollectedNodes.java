package org.projectzion.game.persitence.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class CollectedNodes implements Serializable {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name=" character_id")
    Character character;

    @ManyToOne
    @JoinColumn(name=" node_id")
    Node node;

    Long nextAvailability;
}
