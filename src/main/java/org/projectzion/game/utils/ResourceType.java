package org.projectzion.game.utils;

import java.util.Arrays;
import java.util.Optional;

public enum ResourceType {
    ORE(0),
    FLOWER(1),
    TREE(2),
    ROCK(3),
    MUSHROOM(4),
    TRANSFER_STATION(5),
    ;

    private final int value;

    ResourceType(int value) {
        this.value = value;
    }

    public static Optional<ResourceType> valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.value == value)
                .findFirst();
    }
}
