package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.OsmMatcher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsmMatcherRepository extends CrudRepository<OsmMatcher, Long> {
}
