package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.CollectedNode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectedNodesRepository extends CrudRepository<CollectedNode,Long> {

    @Query("SELECT c FROM CollectedNode c WHERE c.user.id = :userId")
    public List<CollectedNode> findCollectedNodesByUser(@Param("userId") Long userId);
}
