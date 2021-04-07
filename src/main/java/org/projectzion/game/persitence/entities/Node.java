package org.projectzion.game.persitence.entities;

import org.projectzion.game.utils.json.serializer.PointSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="nodes")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long osmId;

    private Long respawnTime;

    @ManyToOne
    private NodeType nodeType;

    @Column(nullable=false)
    @JsonSerialize(using = PointSerializer.class)
    private Point gps;

    @OneToMany(mappedBy = "node")
    private Set<CollectedNodes> collectedNodes;

    @ManyToOne
    private Tile tile;
}
