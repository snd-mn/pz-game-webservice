package org.projectzion.game.persitence.entities;

import java.math.BigDecimal;
import javax.persistence.*;

import org.projectzion.game.persitence.entities.security.User;
import lombok.Data;

@Data
@Entity
@Table(name="inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    Character character;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;
    BigDecimal item_amount;
}
