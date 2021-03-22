package com.example.restservice.tos;

import java.util.List;

public class OverpassTurboResult {
    List<OverpassTurboElement> elements;

    public List<OverpassTurboElement> getElements() {
        return elements;
    }

    public void setElements(List<OverpassTurboElement> elements) {
        this.elements = elements;
    }
}
