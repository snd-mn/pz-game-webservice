package org.projectzion.game.tos;

import lombok.Data;

import java.util.Map;

@Data
public class NodeTo {
    private Long id;
    double lon, lat;
    long nextAvailability;
    private Long nodeType;
}
