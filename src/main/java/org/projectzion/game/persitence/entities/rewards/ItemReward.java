package org.projectzion.game.persitence.entities.rewards;

import lombok.Data;
import org.projectzion.game.persitence.entities.Item;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Entity
public class ItemReward extends Reward{
    @ManyToOne
    Item item;
    double itemAmount;
}
