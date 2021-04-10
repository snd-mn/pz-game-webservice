package org.projectzion.game.tos;

import lombok.Data;

import java.util.List;

@Data
public class TileTo {
    int x, y;
    List<NodeTo> nodeTos;
}
