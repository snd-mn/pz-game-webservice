package org.projectzion.game.tos;

import lombok.Data;
import org.projectzion.game.persitence.entities.Tile;

import java.util.List;

@Data
public class TilesResponse {
    List<TileTo> tiles;
}
