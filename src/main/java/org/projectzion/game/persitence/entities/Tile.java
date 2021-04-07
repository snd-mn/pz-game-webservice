package org.projectzion.game.persitence.entities;

import lombok.Data;
import org.projectzion.game.persitence.entities.security.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name="tiles")
public class Tile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    int x;
    int y;

    Date lastFactoryRun;

    @OneToMany(mappedBy = "tile")
    Set<Node> nodes;


}
