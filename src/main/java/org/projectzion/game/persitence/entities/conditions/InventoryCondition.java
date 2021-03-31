package org.projectzion.game.persitence.entities.conditions;

import org.projectzion.game.persitence.entities.bases.Condition;
import java.math.BigDecimal;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class InventoryCondition extends Condition {
    Long item;
    BigDecimal itemAmount;
}
