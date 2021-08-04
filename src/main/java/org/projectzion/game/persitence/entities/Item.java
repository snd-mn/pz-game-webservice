package org.projectzion.game.persitence.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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

    //EFFECTS e.g. increase spawntime blub | increase gather amount blub | notifications | Achievment
}
