package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.UserNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNodeRepository extends CrudRepository<UserNode, Long> {

}
