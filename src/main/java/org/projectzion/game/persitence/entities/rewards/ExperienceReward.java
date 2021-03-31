package org.projectzion.game.persitence.entities.rewards;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ExperienceReward extends Reward{
    Long experience;
}
