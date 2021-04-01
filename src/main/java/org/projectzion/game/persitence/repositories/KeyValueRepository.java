package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.misc.KeyValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyValueRepository extends CrudRepository<KeyValue, String> {
    @Query("SELECT kv FROM KeyValue kv where kv.key = :key")
    KeyValue findByKey(@Param("key")String key);
}
