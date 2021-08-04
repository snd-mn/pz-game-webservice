package org.projectzion.game.persitence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="collected_nodes")
public class CollectedNodes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;


    @ManyToOne
    @JoinColumn(name="node_id")
    private Node node;

    Long nextAvailability;
}
