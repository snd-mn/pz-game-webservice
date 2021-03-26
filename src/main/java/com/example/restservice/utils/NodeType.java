package com.example.restservice.utils;

import java.util.Arrays;
import java.util.Optional;

public enum NodeType {
    ORE(0),
    FLOWER(1),
    TREE(2),
    ROCK(3),
    MUSHROOM(4),
    TRANSFER_STATION(5),
    ;

    private final int value;

    NodeType(int value) {
        this.value = value;
    }

    public static Optional<NodeType> valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.value == value)
                .findFirst();
    }
}
