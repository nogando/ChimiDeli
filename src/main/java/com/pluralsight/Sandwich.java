package com.pluralsight;

import java.util.ArrayList;

public class Sandwich implements CartItem {

    private String size; // 4, 8, 12
    private String bread; // White, Wheat, Rye, Wrap
    private boolean toasted; // True if toasted
    private ArrayList<Topping> toppings; // since sandwiches are made of toppings
    private double basePrice; // Starting price will be added by toppings

    // Constructor
    public Sandwich(String size, String bread, boolean toasted, double basePrice){
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
        this.basePrice = basePrice;
        this.toppings = new ArrayList<>();
    }

    // Getters


    public double getBasePrice() {
        return basePrice;
    }

    public String getBread() {
        return bread;
    }

    public String getSize() {
        return size;
    }

    public boolean isToasted() {
        return toasted;
    }


    // Method to add topping to Sandwich
    public void addTopping(Topping topping){
        toppings.add(topping);
    }


    // Add total price of basePrice + Toppings
    @Override
    public double getPrice(){
        double total = basePrice;
        for (Topping t : toppings){
            total += t.getPrice();
        }
        return total;
    }

    // Display Info
    @Override
    public String toString() {
        return "Sandwich{" +
                "basePrice=" + basePrice +
                ", size='" + size + '\'' +
                ", bread='" + bread + '\'' +
                ", toasted=" + toasted +
                ", toppings=" + toppings +
                '}';
    }
}
