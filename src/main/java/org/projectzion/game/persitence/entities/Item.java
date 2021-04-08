package org.projectzion.game.persitence.entities;

import javax.persistence.*;

import lombok.Data;
import org.projectzion.game.persitence.entities.conditions.ItemCondition;

import java.util.Collection;

@Data
@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    //TODO localization
    String name;

    boolean tradeAble = false;

    //TODO why did i added this?
    boolean useAble = false;

    @OneToMany(mappedBy = "item")
    Collection<ItemCondition> itemConditions;

    //EFFECTS e.g. increase spawntime blub | increase gather amount blub | notifications | Achievment
}
