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
    @Column(nullable = false)
    int x;
    @Column(nullable = false)
    int y;
    @Column(nullable = false)
    double bboxEast;
    @Column(nullable = false)
    double bboxWest;
    @Column(nullable = false)
    double bboxSouth;
    @Column(nullable = false)
    double bboxNorth;

    Date lastFactoryRun;

    @OneToMany(mappedBy = "tile")
    Set<Node> nodes;


}
