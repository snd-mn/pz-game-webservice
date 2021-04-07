package org.projectzion.game.persitence.repositories;

import org.projectzion.game.persitence.entities.Tile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TileRepository extends CrudRepository<Tile, String> {
    @Query("SELECT t FROM Tile t where T.x = :x and T.y = :y")
    Tile findByXY(@Param("x")int x, @Param("y")int y);
}
