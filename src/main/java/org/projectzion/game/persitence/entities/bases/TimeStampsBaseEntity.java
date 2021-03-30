package org.projectzion.game.persitence.entities.bases;

import org.projectzion.game.persitence.entities.Localized;
import org.projectzion.game.persitence.entities.security.User;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TimeStampsBaseEntity {
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="NAME_ID")
    private Localized nameStrings = new Localized();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="DESCRIPTION_ID")
    private Localized descriptionStrings = new Localized();

    private Date created;

    @ManyToOne
    private User createdByUser;

    private Date modified;

    @ManyToOne
    private User modifiedByUser;
}
