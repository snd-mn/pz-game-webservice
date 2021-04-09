package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character,Long> {
}
