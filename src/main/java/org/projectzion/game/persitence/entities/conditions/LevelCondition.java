package org.projectzion.game.persitence.entities.conditions;

import org.projectzion.game.persitence.entities.bases.ConditionEntity;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class LevelCondition extends ConditionEntity {
     int level;
}