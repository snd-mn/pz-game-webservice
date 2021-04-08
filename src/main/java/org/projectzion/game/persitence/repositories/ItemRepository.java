package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.Item;
import org.projectzion.game.persitence.entities.OsmMatcherNodeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
