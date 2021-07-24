package org.projectzion.game.persitence.entities.conditions;

import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.NodeType;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="conditions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToMany(mappedBy = "conditions")
    Set<NodeType> nodeTypes;
}
