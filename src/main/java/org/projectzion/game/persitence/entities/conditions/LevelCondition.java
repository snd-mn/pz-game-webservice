package org.projectzion.game.persitence.entities.conditions;

import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class LevelCondition extends Condition {
     int level;
}
