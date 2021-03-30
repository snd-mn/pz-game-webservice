package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.UserReward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRewardRepository extends CrudRepository<UserReward, Long> {

}
