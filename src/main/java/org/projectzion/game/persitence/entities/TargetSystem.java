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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String ip;
    String user;
    String password;
    String token;
    //TODO API KEY

}
