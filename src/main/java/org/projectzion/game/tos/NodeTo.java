package org.projectzion.game.tos;

import lombok.Data;

import java.util.Map;

@Data
public class NodeTo {
    private Long id;
    double lon, lat;
    int nextRespawnFromNow;
    int cooldown;
    Map<Long,Double> rewardIdToAmount;
    byte displayType;
    byte conditionFlags;

}
