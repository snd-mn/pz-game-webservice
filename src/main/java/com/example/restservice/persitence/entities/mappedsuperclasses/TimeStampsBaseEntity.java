package com.example.restservice.persitence.entities.mappedsuperclasses;

import com.example.restservice.persitence.entities.Localized;
import com.example.restservice.persitence.entities.User;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
