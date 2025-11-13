package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;

    // Constructor
    public Cart() {
        sandwiches = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
    }
    // Copy Constructor to make checkout work
    public Cart(Cart other) {
        this.sandwiches = new ArrayList<>(other.getSandwiches());
        this.drinks = new ArrayList<>(other.getDrinks());
        this.chips = new ArrayList<>(other.getChips());
    }


    // Add items methods
    public void addSandwich(Sandwich s) {
        sandwiches.add(s);
    }

    public void addDrink(Drink d) {
        drinks.add(d);
    }

    public void addChips(Chips c) {
        chips.add(c);
    }

    // Remove items methods
    public Sandwich removeSandwich(int index) {
        if (index >= 0 && index < sandwiches.size()) {
            return sandwiches.remove(index);
        }
        return null;
    }

    public Drink removeDrink(int index) {
        if (index >= 0 && index < drinks.size()) {
            return drinks.remove(index);
        }
        return null;
    }

    public Chips removeChips(int index) {
        if (index >= 0 && index < chips.size()) {
            return chips.remove(index);
        }
        return null;
    }

    // Check if cart is empty
    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }

    // Calculate total
    public double getTotal() {
        double total = 0;
        for (Sandwich s : sandwiches) total += s.getPrice();
        for (Drink d : drinks) total += d.getPrice();
        for (Chips c : chips) total += c.getPrice();
        return total;
    }

    // View cart contents
    public String view() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Your Cart ===\n");

        if (!sandwiches.isEmpty()) {
            sb.append("\nSandwiches:\n");
            for (int i = 0; i < sandwiches.size(); i++) {
                sb.append("  ").append(i + 1).append(") ").append(sandwiches.get(i)).append("\n");
            }
        }

        if (!drinks.isEmpty()) {
            sb.append("\nDrinks:\n");
            for (int i = 0; i < drinks.size(); i++) {
                sb.append("  ").append(i + 1).append(") ").append(drinks.get(i)).append("\n");
            }
        }

        if (!chips.isEmpty()) {
            sb.append("\nChips:\n");
            for (int i = 0; i < chips.size(); i++) {
                sb.append("  ").append(i + 1).append(") ").append(chips.get(i)).append("\n");
            }
        }

        sb.append(String.format("\nTotal: $%.2f\n", getTotal()));
        return sb.toString();
    }

    // Getters for lists
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }
}
