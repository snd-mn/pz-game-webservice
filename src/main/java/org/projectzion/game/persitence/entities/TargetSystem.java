package org.projectzion.game.persitence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

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

    @OneToMany(mappedBy = "targetSystem")
    Set<Character> character;
}
