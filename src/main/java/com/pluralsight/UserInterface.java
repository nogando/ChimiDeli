package com.pluralsight;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

    // This is where all the customer's selected items are stored
    private Cart cart = new Cart();

    // Used to take input from the user
    private Scanner scanner = new Scanner(System.in);

    // This method runs the whole program
    public void start(){

        // This is the text that shows the main menu options
        String mainMenu = """
            ========= ChimiDeli =========
            1) Add Sandwich
            2) Add Drink
            3) Add Chips
            4) View Cart
            5) Checkout
            0) Exit
            Choose an option:\n""";

        // Keeps the program running until the user chooses to exit
        while(true){
            System.out.println(mainMenu);
            int choice = -1; // default value in case something goes wrong

            // Makes sure the user types a number instead of text or symbols
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // clears the leftover "Enter" key press
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
                continue; // restarts the loop if user enters something invalid
            }

            // Decides what to do based on the number the user typed
            switch (choice){
                case 1 -> addSandwich();  // user wants to build a sandwich
                case 2 -> addDrink();     // user wants to add a drink
                case 3 -> addChips();     // user wants chips
                case 4 -> System.out.println(cart.view()); // show what's in the cart
                case 5 -> checkout();     // checkout (still to be built)
                case 0 -> {               // exit the program
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // Placeholder for the checkout feature (to be finished later)
    private void checkout() {
        //todo build this later
    }

    // ========== CHIPS SECTION ==========
    private void addChips() {
        System.out.println("\n========= Add Chips =========");

        boolean adding = true; // lets user keep adding chips
        while (adding) {
            try {
                // Ask user for chip flavor
                System.out.println("""
        \n====== Chip Flavors ======
        1) Lays Original
        2) Doritos Nacho Cheese
        3) Cheetos
        4) Sun Chips
        5) BBQ Chips
        """);
                System.out.print("Choose your chips: ");
                int flavorChoice = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                // Convert number to flavor
                String flavor = switch (flavorChoice) {
                    case 1 -> "Lays Original";
                    case 2 -> "Doritos Nacho Cheese";
                    case 3 -> "Cheetos";
                    case 4 -> "Sun Chips";
                    case 5 -> "BBQ Chips";
                    default -> {
                        System.out.println("Invalid option. Defaulting to Lays Original.");
                        yield "Lays Original";
                    }
                };

                // Create new Chips object (flat price inside class)
                Chips chips = new Chips(flavor);

                // Add to cart
                cart.addChips(chips);
                System.out.println("Chips added to cart: " + chips);

                // Ask if they want to add more
                System.out.print("\nAdd another chips? (y/n): ");
                adding = scanner.nextLine().trim().equalsIgnoreCase("y");

            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    // ========== DRINK SECTION ==========
    private void addDrink() {
        System.out.println("\n========= Add a Drink =========");

        boolean adding = true; // allows user to keep adding drinks
        while (adding) {
            try {
                // Ask user for drink size
                System.out.println("""
        \n====== Size ======
        1) Small  - $1.50
        2) Medium - $2.00
        3) Large  - $2.50
        """);
                System.out.print("Choose size: ");
                int sizeChoice = scanner.nextInt();
                scanner.nextLine();

                // Convert the number to a word (like "Small")
                String size = switch (sizeChoice) {
                    case 1 -> "Small";
                    case 2 -> "Medium";
                    case 3 -> "Large";
                    default -> {
                        System.out.println("Invalid option. Defaulting to Medium.");
                        yield "Medium";
                    }
                };

                // Ask user for drink type
                System.out.println("""
        \n====== Drink Type ======
        1) Coke
        2) Sprite
        3) Fanta
        4) Water
        5) Iced Tea
        """);
                System.out.print("Choose your drink: ");
                int typeChoice = scanner.nextInt();
                scanner.nextLine();

                // Convert number to the drink name
                String name = switch (typeChoice) {
                    case 1 -> "Coke";
                    case 2 -> "Sprite";
                    case 3 -> "Fanta";
                    case 4 -> "Water";
                    case 5 -> "Iced Tea";
                    default -> {
                        System.out.println("Invalid option. Defaulting to Water.");
                        yield "Water";
                    }
                };

                // Ask if they want ice
                System.out.print("\nIce? (y/n): ");
                boolean ice = scanner.nextLine().trim().equalsIgnoreCase("y");

                // Create a new Drink object
                Drink drink = new Drink(name + (ice ? " w/ Ice" : ""), size);

                // Add the drink to the cart
                cart.addDrink(drink);
                System.out.println("Drink added to cart: " + drink);

                // Ask if they want to add another drink
                System.out.print("\nAdd another drink? (y/n): ");
                adding = scanner.nextLine().trim().equalsIgnoreCase("y");

            } catch (InputMismatchException e) {
                // Handles input errors (like typing a letter instead of a number)
                System.out.println("Wrong input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // ========== SANDWICH SECTION ==========
    private void addSandwich() {
        System.out.println("\n========= Build Your Sandwich =========");

        try {
            // Ask for sandwich size
            System.out.println("""
        \n====== Size ======
        1) 4"  - $5.50
        2) 8"  - $7.00
        3) 12" - $8.50
        """);
            System.out.print("Choose your sandwich size: ");
            int sizeChoice = scanner.nextInt();
            scanner.nextLine();

            // Variables for size and base price
            String size;
            double basePrice;

            // Match user's number to size and price
            switch (sizeChoice) {
                case 1 -> { size = "4"; basePrice = 5.50; }
                case 2 -> { size = "8"; basePrice = 7.00; }
                case 3 -> { size = "12"; basePrice = 8.50; }
                default -> {
                    System.out.println("Invalid option. Defaulting to 8.");
                    size = "8";
                    basePrice = 7.00;
                }
            }

            // Ask for bread type
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

            // Match bread type
            String bread = switch (breadChoice) {
                case 1 -> "White";
                case 2 -> "Wheat";
                case 3 -> "Rye";
                case 4 -> "Wrap";
                default -> "Wheat";
            };

            // Ask if sandwich should be toasted
            System.out.print("\nWould you like it toasted? (y/n): ");
            boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("y");

            // Create new Sandwich object
            Sandwich sandwich = new Sandwich(size, bread, toasted, basePrice);

            // Ask for meats
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
            addToppings2(sandwich, "meat", meatPrice(size), size);

            // Ask for cheeses
            System.out.println("""
        \n====== Cheeses ======
        1) American
        2) Provolone
        3) Swiss
        4) Cheddar
        0) None
        """);
            System.out.print("Select your cheeses (separate with spaces): ");
            addToppings2(sandwich, "cheese", cheesePrice(size), size);

            // Ask for regular toppings
            System.out.println("""
        \n====== Regular Toppings ======
         1) Lettuce
         2) Peppers
         3) Onions
         4) Tomatoes
         5) Jalapeños
         6) Cucumbers
         7) Pickles
         8) Guacamole
         9) Mushrooms
         0) None
         """);
            System.out.print("Select your toppings (separate with spaces): ");
            addToppings2(sandwich, "regular", 0.0, size);

            // Ask for sauces
            System.out.println("""
        \n====== Sauces ======
        1) Mayo
        2) Mustard
        3) Ketchup
        4) Ranch
        5) Thousand Islands
        6) Vinaigrette
        0) None
        """);
            System.out.print("Select your sauces (separate with spaces): ");
            addToppings2(sandwich, "sauce", 0.0, size);

            // Add sandwich to cart
            cart.addSandwich(sandwich);
            System.out.println("\nSandwich added to cart!");

        } catch (InputMismatchException e) {
            // Stops the program from crashing when wrong input is entered
            System.out.println("Wrong input. Please enter a number.");
            scanner.nextLine();
        }
    }

    // ========== ADD TOPPINGS ==========
    // This method handles all toppings for sandwiches.
    // It figures out which list to use (meat, cheese, etc.),
    // adds the toppings to the sandwich, and asks if they want extras.
    private void addToppings2(Sandwich sandwich, String type, double price, String size) {
        // Different topping lists for each category
        String[] meatChoices    = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};
        String[] cheeseChoices  = {"American", "Provolone", "Swiss", "Cheddar"};
        String[] regularChoices = {"Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños","Cucumbers", "Pickles", "Guacamole", "Mushrooms"};
        String[] sauceChoices   = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};

        // Pick the correct list based on type
        String[] options = switch (type) {
            case "meat"    -> meatChoices;
            case "cheese"  -> cheeseChoices;
            case "regular" -> regularChoices;
            case "sauce"   -> sauceChoices;
            default        -> new String[0];
        };

        // Get user input like: "1 3 4"
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return;

        for (String userInput : input.split("\\s+")) {
            try {
                int num = Integer.parseInt(userInput);
                if (num <= 0 || num > options.length) continue; // skip invalid
                String name = options[num - 1];

                // Regular and sauces are free. Meats and cheese cost extra.
                double toppingPrice = (type.equals("meat") || type.equals("cheese")) ? price : 0.0;
                sandwich.addTopping(new Topping(name, type, toppingPrice));

                // Ask about extras for meat and cheese
                if (type.equals("meat") || type.equals("cheese")) {
                    double extraPrice = type.equals("meat") ? extraMeatPrice(size) : extraCheesePrice(size);
                    System.out.print("Would you like extra " + name + " for $" + extraPrice + "? (y/n): ");
                    String extra = scanner.nextLine().trim().toLowerCase();

                    if (extra.equals("y")) {
                        sandwich.addTopping(new Topping("Extra " + name, type, extraPrice));
                        System.out.println("Extra " + name + " added!");
                    }
                }

            } catch (NumberFormatException ignored) {
                // skip if user typed something that isn’t a number
            }
        }
    }

    // Meat topping prices by sandwich size
    private double meatPrice(String size) {
        return switch (size) {
            case "4" -> 1.00;
            case "8" -> 2.00;
            case "12" -> 3.00;
            default -> 2.00;
        };
    }

    // Cheese topping prices by sandwich size
    private double cheesePrice(String size) {
        return switch (size) {
            case "4" -> 0.75;
            case "8" -> 1.50;
            case "12" -> 2.25;
            default -> 1.50;
        };
    }

    // Extra meat cost by sandwich size
    private double extraMeatPrice(String size) {
        return switch (size) {
            case "4" -> 0.50;
            case "8" -> 1.00;
            case "12" -> 1.50;
            default -> 1.00;
        };
    }

    // Extra cheese cost by sandwich size
    private double extraCheesePrice(String size) {
        return switch (size) {
            case "4" -> 0.30;
            case "8" -> 0.60;
            case "12" -> 0.90;
            default -> 0.60;
        };
    }
}

