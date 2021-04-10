package org.projectzion.game.persitence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="collected_nodes")
public class CollectedNodes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name="character_id")
    private Character character;

    @ManyToOne
    @JoinColumn(name="node_id")
    private Node node;

    Long nextAvailability;
}
