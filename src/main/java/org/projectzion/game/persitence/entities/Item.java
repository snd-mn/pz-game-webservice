package org.projectzion.game.persitence.entities;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Boolean tradeAble;

    Boolean useAble;

    //EFFECTS e.g. increase spawntime blub | increase gather amount blub | notifications | Achievment
}
