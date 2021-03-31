package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.CollectedNodes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNodeRepository extends CrudRepository<CollectedNodes, Long> {

}
