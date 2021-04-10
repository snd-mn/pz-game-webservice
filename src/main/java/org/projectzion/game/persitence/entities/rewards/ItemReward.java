package org.projectzion.game.persitence.entities.rewards;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.Item;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ItemReward extends Reward{
    @ManyToOne
    Item item;
    double itemAmount;
}
