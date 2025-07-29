package com.niua.adapter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bucket {

    private String name;
    private int value;

    public Bucket(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Bucket() {

    }
}
