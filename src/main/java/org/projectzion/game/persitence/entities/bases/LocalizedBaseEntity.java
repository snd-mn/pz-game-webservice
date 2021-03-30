package org.projectzion.game.persitence.entities.bases;

import org.projectzion.game.persitence.entities.Localized;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LocalizedBaseEntity {
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="NAME_ID")
    private Localized nameStrings = new Localized();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="DESCRIPTION_ID")
    private Localized descriptionStrings = new Localized();
}
