package com.example.restservice.tos;

import java.math.BigDecimal;
import java.util.HashMap;

public class OverpassTurboElement {
    String type;
    long id;
    BigDecimal lat;
    BigDecimal lon;
    HashMap<String,String> tags;
}
