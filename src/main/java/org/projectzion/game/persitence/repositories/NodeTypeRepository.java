package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.NodeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeTypeRepository extends CrudRepository<NodeType, Long> {

}
