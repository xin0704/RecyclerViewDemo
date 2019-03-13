package com.xin.recyclerviewdemo.entity;

import java.util.List;

/**
 * Created by admin on 2019/3/12.
 */

public class Province {

    private String name;

    private List<City> city;

    private String sortLetters;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public List<City> getCity() {
        return city;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}
