package org.projectzion.game.persitence.entities.rewards;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class ExperienceReward extends Reward{
    Long experience;
}
