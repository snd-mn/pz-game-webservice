package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.CollectedNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNodeRepository extends CrudRepository<CollectedNode, Long> {

}
