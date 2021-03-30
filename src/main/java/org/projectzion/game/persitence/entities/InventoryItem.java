package org.projectzion.game.persitence.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.projectzion.game.persitence.entities.security.User;
import lombok.Data;

@Data
@Entity
public class InventoryItem {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

    BigDecimal amount;
}
