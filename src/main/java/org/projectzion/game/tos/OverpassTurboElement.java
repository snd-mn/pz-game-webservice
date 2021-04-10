package org.projectzion.game.tos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class OverpassTurboElement {
    String type;
    long id;
    double lat;
    double lon;
    Map<String,String> tags;
}
