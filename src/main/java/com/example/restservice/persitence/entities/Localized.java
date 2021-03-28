package com.example.restservice.persitence.entities;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Localized {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int dummy = 0;

    @ElementCollection
    private Map<String, String> strings = new HashMap<String, String>();

    //private String locale;
    //private String text;

    public Localized() {
    }

    public Localized(Map<String, String> map) {
        this.strings = map;
    }

    public void addString(String locale, String text) {
        strings.put(locale, text);
    }

    public String getString(String locale) {
        String returnValue = strings.get(locale);
        return (returnValue != null ? returnValue : null);
    }
}
