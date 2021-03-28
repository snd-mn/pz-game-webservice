package com.example.restservice.persitence.entities.conditions;

import com.example.restservice.persitence.entities.bases.ConditionBaseEntity;
import java.math.BigDecimal;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class InventoryCondition extends ConditionBaseEntity {
    Long item;
    BigDecimal amount;
}
