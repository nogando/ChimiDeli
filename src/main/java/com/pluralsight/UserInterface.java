package com.pluralsight;

import java.util.Scanner;

public class UserInterface {

    private Cart cart = new Cart();
    private Scanner scanner = new Scanner(System.in);

    public void start(){

        // ===== Main Menu Text =====
        // This is a multi-line string (text block) that shows the main menu options.
        String mainMenu = """
            ========= ChimiDeli =========
            1) Add Sandwich
            2) Add Drink
            3) Add Chips
            4) View Cart
            5) Checkout
            0) Exit
            Choose an option:\n""";

        // ===== Loop until the user chooses to exit =====
        while(true){

            System.out.println(mainMenu); // show the menu
            int choice = scanner.nextInt(); // get user input for the option
            scanner.nextLine(); // clear the leftover newline character

            // ===== Handle the user's menu choice =====
            switch (choice){
                case 1 -> addSandwich(); // if user chooses 1, call addSandwich()
                case 2 -> addDrink();    // addDrink() will handle adding a drink
                case 3 -> addChips();    // addChips() will handle adding chips
                case 4 -> System.out.println(cart.view()); // show what’s in the cart
                case 5 -> checkout();    // go to checkout process
                case 0 -> {              // exit program
                    System.out.println("Goodbye!");
                    return; // ends the loop and method
                }
                default -> System.out.println("Invalid choice."); // handle wrong input
            }

        }

    }

    private void checkout() {
        // This method will handle payment and finishing the order later
    }

    private void addChips() {
        // This will let the user pick a type of chips later
    }

    private void addDrink() {
        // This will let the user pick a drink later
    }

    private void addSandwich() {
        System.out.println("\n========= Build Your Sandwich =========");

        // ===== Size selection =====
        // Ask for the sandwich size and base price
        System.out.println("""
        \n====== Size ======
        1) 4"  - $5.50
        2) 8"  - $7.00
        3) 12" - $8.50
        """);
        System.out.print("Choose your sandwich size: ");
        int sizeChoice = scanner.nextInt(); // user chooses size
        scanner.nextLine(); // clear newline

        String size;
        double basePrice;

        // match the choice to actual size and price
        switch (sizeChoice) {
            case 1 -> { size = "4\""; basePrice = 5.50; }
            case 2 -> { size = "8\""; basePrice = 7.00; }
            case 3 -> { size = "12\""; basePrice = 8.50; }
            default -> {
                // if input is wrong, default to 8-inch
                System.out.println("Invalid option. Defaulting to 8\".");
                size = "8\"";
                basePrice = 7.00;
            }
        }

        // ===== Bread selection =====
        // Ask what type of bread the user wants
        System.out.println("""
        \n====== Bread Type ======
        1) White
        2) Wheat
        3) Rye
        4) Wrap
        """);
        System.out.print("Choose your bread: ");
        int breadChoice = scanner.nextInt();
        scanner.nextLine();

        // assign bread based on choice
        String bread = switch (breadChoice) {
            case 1 -> "White";
            case 2 -> "Wheat";
            case 3 -> "Rye";
            case 4 -> "Wrap";
            default -> "Wheat"; // default bread
        };

        // ===== Toasted option =====
        // Ask if they want the sandwich toasted
        System.out.print("\nWould you like it toasted? (y/n): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("y");

        // Create sandwich object with info so far
        Sandwich sandwich = new Sandwich(size, bread, toasted, basePrice);

        // ===== Toppings menus =====
        // Let user choose meats, cheeses, veggies, and sauces.
        System.out.println("""
        \n====== Meats ======
        1) Steak
        2) Ham
        3) Salami
        4) Roast Beef
        5) Chicken
        6) Bacon
        0) None
        """);
        System.out.print("Select your meats (separate with spaces): ");
        addToppings(sandwich, "meat", meatPrice(size)); // call helper method

        System.out.println("""
        \n====== Cheeses ======
        1) American
        2) Provolone
        3) Swiss
        4) Cheddar
        0) None
        """);
        System.out.print("Select your cheeses: ");
        addToppings(sandwich, "cheese", cheesePrice(size));

        System.out.println("""
        \n====== Veggies ======
        1) Lettuce
        2) Tomato
        3) Onion
        4) Pickles
        5) Peppers
        0) None
        """);
        System.out.print("Select your veggies: ");
        addToppings(sandwich, "regular", 0.0);

        System.out.println("""
        \n====== Sauces ======
        1) Mayo
        2) Mustard
        3) Ranch
        4) Chipotle
        0) None
        """);
        System.out.print("Select your sauces: ");
        addToppings(sandwich, "sauce", 0.0);

        // Add the finished sandwich to the shopping cart
        cart.addSandwich(sandwich);
        System.out.println("\nSandwich added to cart!");
    }


    // ===== Helper Method to Add Toppings =====
    private void addToppings(Sandwich sandwich, String type, double price) {
        // Read the user input line of numbers separated by spaces
        String input = scanner.nextLine();
        String[] choices = input.split(" "); // split into array

        // Loop through each number and add the correct topping
        for (String c : choices) {
            try {
                int num = Integer.parseInt(c); // convert to int
                if (num == 0) continue; // skip if user entered 0 (none)
                // Determine the topping name based on type + number
                String name = switch (type + num) {
                    case "meat1" -> "Steak";
                    case "meat2" -> "Ham";
                    case "meat3" -> "Salami";
                    case "meat4" -> "Roast Beef";
                    case "meat5" -> "Chicken";
                    case "meat6" -> "Bacon";
                    case "cheese1" -> "American";
                    case "cheese2" -> "Provolone";
                    case "cheese3" -> "Swiss";
                    case "cheese4" -> "Cheddar";
                    case "regular1" -> "Lettuce";
                    case "regular2" -> "Tomato";
                    case "regular3" -> "Onion";
                    case "regular4" -> "Pickles";
                    case "regular5" -> "Peppers";
                    case "sauce1" -> "Mayo";
                    case "sauce2" -> "Mustard";
                    case "sauce3" -> "Ranch";
                    case "sauce4" -> "Chipotle";
                    default -> null; // if number doesn’t match, ignore
                };
                // if name is valid, add topping to sandwich
                if (name != null) sandwich.addTopping(new Topping(name, type, price));
            } catch (NumberFormatException ignored) {
                // ignore bad input like letters
            }
        }
    }

    // ===== Price based on sandwich size =====
    private double meatPrice(String size) {
        return switch (size) {
            case "4\"" -> 1.00;
            case "8\"" -> 2.00;
            case "12\"" -> 3.00;
            default -> 2.00;
        };
    }

    private double cheesePrice(String size) {
        return switch (size) {
            case "4\"" -> 0.75;
            case "8\"" -> 1.50;
            case "12\"" -> 2.25;
            default -> 1.50;
        };
    }

}















//adding supporting methods such as toppings list to add to sub





