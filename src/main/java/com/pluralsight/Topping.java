package com.pluralsight;

public class Topping implements CartItem {

    private String name;   // Chicken, Swiss, Ranch
    private String type;   // meat, cheese, sauce, regular
    private double price;  // Base price for that topping

    // Constructor
    public Topping(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // Default price
    @Override
    public double getPrice() {
        return price;
    }

    // Display Info
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}

