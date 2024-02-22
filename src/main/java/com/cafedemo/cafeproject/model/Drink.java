package com.cafedemo.cafeproject.model;

public class Drink {

    private String type;
    private String name;
    private double weight;
    private double price;
    private int roastingLevel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getRoastingLevel() {
        return roastingLevel;
    }

    public void setRoastingLevel(int roastingLevel) {
        this.roastingLevel = roastingLevel;
    }
}
