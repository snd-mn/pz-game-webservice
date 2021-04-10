package org.projectzion.game.persitence.entities.conditions;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.Item;

@Getter
@Setter
@Entity
public class ItemCondition extends Condition {
    @ManyToOne
    Item item;
    BigDecimal itemAmount;
}
