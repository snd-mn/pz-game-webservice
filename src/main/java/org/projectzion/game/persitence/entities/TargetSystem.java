package org.projectzion.game.persitence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
public class TargetSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    java.net.InetAddress ip;
    String user;
    String password;
    String token;
    //TODO API KEY

    @OneToMany(mappedBy = "targetSystem")
    Set<Character> character;
}
