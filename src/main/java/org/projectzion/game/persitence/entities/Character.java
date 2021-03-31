package org.projectzion.game.persitence.entities;

import lombok.Data;
import org.projectzion.game.persitence.entities.security.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name=" user_id")
    User user;

    @ManyToOne
    @JoinColumn(name=" target_system_id")
    TargetSystem targetSystem;

    @ManyToOne
    @JoinColumn(name="inventory_id")
    Inventory inventory;

    @OneToMany(mappedBy = "character")
    private Set<CollectedNodes> collectedNodes;

    boolean enabled;



}
