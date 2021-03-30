package org.projectzion.game.persitence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Item {
    @Id
    Long id;

    Boolean tradeAble;

    Boolean useAble;

    //EFFECTS e.g. increase spawntime blub | increase gather amount blub | notifications | Achievment
}
