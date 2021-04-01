package org.projectzion.game.persitence.entities.misc;

import lombok.Data;
import org.apache.catalina.core.ApplicationContext;
import org.projectzion.game.persitence.entities.security.User;

import javax.persistence.*;

@Data
@Entity
@Table(name = "key_values")
public class KeyValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "the_key", unique = true, nullable = false)
    String key;

    @Column(name = "the_value")
    String value;
}
