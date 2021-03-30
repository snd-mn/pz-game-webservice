package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.NodeTypeReward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeTypeRewardRepository extends CrudRepository<NodeTypeReward, Long> {

}
