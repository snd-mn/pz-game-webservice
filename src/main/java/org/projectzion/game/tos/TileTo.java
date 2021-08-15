package org.projectzion.game.tos;

import lombok.Data;

import java.util.List;

@Data
public class TileTo {
    Long id;
    Long x, y;
    List<NodeTo> nodes;
}
