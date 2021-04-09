package org.projectzion.game.tos;

import lombok.Data;
import org.projectzion.game.utils.Gps;

@Data
public class TilesRequest {
    long characterId;
    Gps gps;
}
