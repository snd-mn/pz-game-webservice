package org.projectzion.game.tos;

import lombok.Data;

@Data
public class InteractionResponse {
    public static final String FINE = "fine!";
    public static final String FAIL = "fail!";
    String info;
}
