package org.projectzion.game.persitence.entities.bases;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ConditionBaseEntity extends LocalizedBaseEntity{

    @Id
    Long id;
}
