package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.Node;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {

    @Query("SELECT n FROM Node n WHERE n.osmId IN :osmIds")
    List<Node> findAllByOsmIds(@Param("osmIds") List<Long> osmIds);

}
