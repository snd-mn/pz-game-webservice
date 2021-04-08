package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.NodeTypeReward;
import org.projectzion.game.persitence.entities.OsmMatcherNodeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsmMatcherNodeTypeRepository extends CrudRepository<OsmMatcherNodeType, Long> {
}
