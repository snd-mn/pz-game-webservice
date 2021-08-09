package org.projectzion.game.persitence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="target_systems")
public class TargetSystem {
    @Id
    Long id;

    String name;
}
