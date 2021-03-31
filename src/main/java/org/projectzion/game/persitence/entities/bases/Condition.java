package org.projectzion.game.persitence.entities.bases;

import org.projectzion.game.persitence.entities.NodeType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="conditions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToMany
    Set<NodeType> nodeTypes;
}
