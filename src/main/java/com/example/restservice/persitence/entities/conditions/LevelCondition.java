package com.example.restservice.persitence.entities.conditions;

import com.example.restservice.persitence.entities.bases.ConditionBaseEntity;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class LevelCondition extends ConditionBaseEntity {
     int level;
}
