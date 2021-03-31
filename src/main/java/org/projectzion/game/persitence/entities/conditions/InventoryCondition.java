package org.projectzion.game.persitence.entities.conditions;

import org.projectzion.game.persitence.entities.bases.ConditionEntity;
import java.math.BigDecimal;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class InventoryCondition extends ConditionEntity {
    Long item;
    BigDecimal itemAmount;
}
