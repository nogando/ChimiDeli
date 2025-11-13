package com.pluralsight;

public class Drink implements CartItem {

    private String flavor;   // Coke, Sprite, Lemonade
    private String size;     // Small, Medium, Large
    private double price;    // Set by size inside constructor

    public Drink(String flavor, String size) {
        this.flavor = flavor;
        this.size = size;
        this.price = switch (size) {
            case "Small"  -> 1.50;
            case "Medium" -> 2.00;
            case "Large"  -> 2.50;
            default       -> 2.00;
        };
    }

    public String getFlavor() { return flavor; }
    public String getSize() { return size; }
    @Override public double getPrice() { return price; }

    // Display Info
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Drink: ").append(flavor)
                .append(" (").append(size).append(")")
                .append("\n");

        sb.append(String.format("  Price: $%.2f", getPrice()));

        return sb.toString();
    }

}

