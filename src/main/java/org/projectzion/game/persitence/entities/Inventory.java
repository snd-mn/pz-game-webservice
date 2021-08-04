package org.projectzion.game.persitence.entities;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;
    BigDecimal item_amount;
}
