package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.TargetSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetSystemRepository extends CrudRepository<TargetSystem, Long> {

}
