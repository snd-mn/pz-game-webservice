package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.rewards.Reward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends CrudRepository<Reward, Long> {

}
