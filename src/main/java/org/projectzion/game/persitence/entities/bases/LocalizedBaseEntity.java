package org.projectzion.game.persitence.entities.bases;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public class LocalizedBaseEntity {
    @ElementCollection
    private Map<String, String> names = new HashMap<String, String>();
    @ElementCollection
    private Map<String, String> descriptions = new HashMap<String, String>();
}
