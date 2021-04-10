package org.projectzion.game.tos;

import lombok.Data;

import java.util.List;

@Data
public class OverpassTurboResult {
    List<OverpassTurboElement> elements;
}
