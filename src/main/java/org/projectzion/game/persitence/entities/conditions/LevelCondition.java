package org.projectzion.game.persitence.entities.conditions;

import org.projectzion.game.persitence.entities.bases.Condition;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class LevelCondition extends Condition {
     int level;
}
