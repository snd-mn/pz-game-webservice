package org.projectzion.game.persitence.entities.conditions;

import javax.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PartyCondition extends Condition {
    int partySize;
}
