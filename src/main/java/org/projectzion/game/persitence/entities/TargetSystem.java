package org.projectzion.game.persitence.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TargetSystem {
    @Id
    Long id;
    String name;
    java.net.InetAddress ip;
    String user;
    String password;
    String token;
    //TODO API KEY
}
