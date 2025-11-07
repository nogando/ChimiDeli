package com.pluralsight;

public class Drink implements CartItem {

    private String flavor;   // Coke, Sprite, Lemonade
    private String size;     // Small, Medium, Large
    private double price;    // Base price depending on size

    // Constructor
    public Drink(String flavor, String size, double price) {
        this.flavor = flavor;
        this.size = size;
        this.price = price;
    }

    // Getters
    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return price;
    }
    // Display Info
    @Override
    public String toString() {
        return "Drink{" +
                "flavor='" + flavor + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }
}
