package org.projectzion.game.persitence.entities.conditions;

import org.projectzion.game.persitence.entities.bases.ConditionBaseEntity;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class PartyCondition extends ConditionBaseEntity {
    int partySize;
}
