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
        //todo This method will handle payment and finishing the order later
    }

    private void addChips() {
        //todo This will let the user pick a type of chips later

    }

    private void addDrink() {
        System.out.println("\n========= Add a Drink =========");

        boolean adding = true;
        while (adding) {
            System.out.println("""
        \n====== Size ======
        1) Small  - $1.50
        2) Medium - $2.00
        3) Large  - $2.50
        """);
            System.out.print("Choose size: ");
            int sizeChoice = scanner.nextInt();
            scanner.nextLine();

            String size = switch (sizeChoice) {
                case 1 -> "Small";
                case 2 -> "Medium";
                case 3 -> "Large";
                default -> {
                    System.out.println("Invalid option. Defaulting to Medium.");
                    yield "Medium";
                }
            };

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

            System.out.print("\nIce? (y/n): ");
            boolean ice = scanner.nextLine().trim().equalsIgnoreCase("y");

            // Price is computed inside Drink based on size
            Drink drink = new Drink(name + (ice ? " w/ Ice" : ""), size);

            cart.addDrink(drink);
            System.out.println("Drink added to cart: " + drink);

            System.out.print("\nAdd another drink? (y/n): ");
            adding = scanner.nextLine().trim().equalsIgnoreCase("y");
        }
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
            case 1 -> { size = "4"; basePrice = 5.50; }
            case 2 -> { size = "8"; basePrice = 7.00; }
            case 3 -> { size = "12"; basePrice = 8.50; }
            default -> {
                // if input is wrong, default to 8-inch
                System.out.println("Invalid option. Defaulting to 8.");
                size = "8";
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
        addToppings2(sandwich, "meat", meatPrice(size),size); // call helper method

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
        System.out.print("Select your sauces (separate with spaces):");
        addToppings2(sandwich, "sauce", 0.0, size);

        // Add the finished sandwich to the shopping cart
        cart.addSandwich(sandwich);
        System.out.println("\nSandwich added to cart!");
    }


    // ===== Helper Method to Add Toppings =====
//    private void addToppings(Sandwich sandwich, String type, double price) {
//        // Read the user input line of numbers separated by spaces
//        String input = scanner.nextLine();
//        String[] choices = input.split(" "); // split into array
//
//        // Loop through each number and add the correct topping
//        for (String c : choices) {
//            try {
//                int num = Integer.parseInt(c); // convert to int
//                if (num == 0) continue; // skip if user entered 0
//                // Determine the topping name based on type + number
//                String name = switch (type + num) {
//
//                    // Meats
//                    case "meat1" -> "Steak";
//
//                    case "meat2" -> "Ham";
//                    case "meat3" -> "Salami";
//                    case "meat4" -> "Roast Beef";
//                    case "meat5" -> "Chicken";
//                    case "meat6" -> "Bacon";
//
//                    // Cheeses
//                    case "cheese1" -> "American";
//                    case "cheese2" -> "Provolone";
//                    case "cheese3" -> "Swiss";
//                    case "cheese4" -> "Cheddar";
//
//                    // Regular Toppings
//                    case "regular1" -> "Lettuce";
//                    case "regular2" -> "Peppers";
//                    case "regular3" -> "Onions";
//                    case "regular4" -> "Tomatoes";
//                    case "regular5" -> "Jalapeños";
//                    case "regular6" -> "Cucumbers";
//                    case "regular7" -> "Pickles";
//                    case "regular8" -> "Guacamole";
//                    case "regular9" -> "Mushrooms";
//
//                    // Sauces
//                    case "sauce1" -> "Mayo";
//                    case "sauce2" -> "Mustard";
//                    case "sauce3" -> "Ketchup";
//                    case "sauce4" -> "Ranch";
//                    case "sauce5" -> "Thousand Islands";
//                    case "sauce6" -> "Vinaigrette";
//                    default -> null; // if number doesn’t match, ignore
//                };
//                // if name is valid, add topping to sandwich
//                if (name != null) sandwich.addTopping(new Topping(name, type, price));
//
//                //todo add a prompt for extra meat and cheese
//                if (type.equals("meat") || type.equals("cheese")) {
//                    System.out.print("Would you like extra " + name + " for $" + price + "? (y/n): ");
//                    String extra = scanner.nextLine().trim().toLowerCase();
//                    if (extra.equals("y")) {
//                        sandwich.addTopping(new Topping("Extra " + name, type, price));
//                        System.out.println("Extra " + name + " added!");
//                    }
//                }
//
//            } catch (NumberFormatException ignored) {
//                // ignore bad input like letters
//            }
//        }
//    }


    // ===== Helper Method to Add Toppings =====
    private void addToppings2(Sandwich sandwich, String type, double price,String size) {
        // Choice arrays per type
        String[] meatChoices    = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};
        String[] cheeseChoices  = {"American", "Provolone", "Swiss", "Cheddar"};
        String[] regularChoices = {"Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños",
                "Cucumbers", "Pickles", "Guacamole", "Mushrooms"};
        String[] sauceChoices   = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};

        // Pick which array to use
        String[] options = switch (type) {
            case "meat"    -> meatChoices;
            case "cheese"  -> cheeseChoices;
            case "regular" -> regularChoices;
            case "sauce"   -> sauceChoices;
            default        -> new String[0];
        };

        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return;

        for (String userInput : input.split("\\s+")) {
            try {
                int num = Integer.parseInt(userInput);
                if (num == 0) continue; // user chose None
                if (num < 1 || num > options.length) continue; // out of range

                String name = options[num - 1];

                //need to add logic for regular or meat and cheese

                double toppingPrice = 0.0; // default free

                if (type.equals("meat") || type.equals("cheese")){
                    sandwich.addTopping(new Topping(name, type, price));
                }
                else{
                    sandwich.addTopping(new Topping(name, type, toppingPrice));
                }



                // offer extra for meat and cheese
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
                // skip non numbers
            }
        }
    }




    // ===== Price based on sandwich size =====
    private double meatPrice(String size) {
        return switch (size) {
            case "4" -> 1.00;
            case "8" -> 2.00;
            case "12" -> 3.00;
            default -> 2.00;
        };
    }

    private double cheesePrice(String size) {
        return switch (size) {
            case "4" -> 0.75;
            case "8" -> 1.50;
            case "12" -> 2.25;
            default -> 1.50;
        };
    }


    // helper methods for Drink
//    private double drinkPrice(String size) {
//        // Returns base price by size
//        return switch (size) {
//            case "Small" -> 1.50;
//            case "Medium" -> 2.00;
//            case "Large" -> 2.50;
//            default -> 2.00;
//        };
//    }

    private double extraMeatPrice(String size){
            return switch (size) {
                case "4" -> .50;
                case "8" -> 1.00;
                case "12" -> 1.50;
                default -> 1.00;
            };
    }

    private double extraCheesePrice(String size) {
        return switch (size) {
            case "4" -> .30;
            case "8" -> .60;
            case "12" -> .90;
            default -> .60;
        };
    }
}















//adding supporting methods such as toppings list to add to sub





