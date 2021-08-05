package org.projectzion.game.persitence.entities;

import lombok.Getter;
import lombok.Setter;
import org.projectzion.game.utils.json.serializer.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="nodes")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long osmId;

    private int respawnTime;

    @ManyToOne
    private NodeType nodeType;

    @Column(nullable=false)
    @JsonSerialize(using = PointSerializer.class)
    private Point gps;

    @OneToMany(mappedBy = "node")
    private Set<CollectedNode> collectedNodes;

    @ManyToOne
    private Tile tile;
}
