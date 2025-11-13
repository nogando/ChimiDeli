package com.pluralsight;

public class Chips implements CartItem {

    private String type;   // Lays, Doritos, Cheetos
    private double price;  // Flat rate for chips

    // Constructor
    public Chips(String flavor) {
        this.type = flavor;
        this.price = 1.50; // flat rate for all chips
    }

    // Getters
    public String getType() {
        return type;
    }

    @Override
    public double getPrice() {
        return price;
    }

    // Display Info
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Chips: ").append(type).append("\n");
        sb.append(String.format("  Price: $%.2f", getPrice()));

        return sb.toString();
    }

}
