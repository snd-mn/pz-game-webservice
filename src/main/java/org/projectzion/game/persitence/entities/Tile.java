package org.projectzion.game.persitence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.persitence.entities.security.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="tiles")
public class Tile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    Long x;
    @Column(nullable = false)
    Long y;
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
