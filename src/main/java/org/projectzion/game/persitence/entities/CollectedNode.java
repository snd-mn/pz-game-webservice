package org.projectzion.game.persitence.entities;

import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.security.User;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="collected_nodes")
public class CollectedNode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name="node_id")
    private Node node;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    Long nextAvailability;
}
