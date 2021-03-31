package org.projectzion.game.persitence.entities.bases;

import org.projectzion.game.persitence.entities.security.User;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TimeStampsBaseEntity {
    private Date created;

    @ManyToOne
    private User createdByUser;

    private Date modified;

    @ManyToOne
    private User modifiedByUser;
}
