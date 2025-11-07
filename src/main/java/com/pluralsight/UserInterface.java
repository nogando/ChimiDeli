package com.pluralsight;

import java.util.Scanner;

public class UserInterface {

    private Cart cart = new Cart();
    private Scanner scanner = new Scanner(System.in);

    public void start(){

        String mainMenu = """
                ========= ChimiDeli =========
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) View Cart
                5) Checkout
                0) Exit
                Choose an option:\n""";
        while(true){

            System.out.println(mainMenu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> addSandwich();
                case 2 -> addDrink();
                case 3 -> addChips();
                case 4 -> System.out.println(cart.view());
                case 5 -> checkout();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }

        }

    }

    private void checkout() {
    }

    private void addChips() {

    }

    private void addDrink() {

    }

    private void addSandwich() {
        System.out.println("\n========= Build Your Sandwich =========");



        // Get the Size of the Sub
        System.out.println("""
                \n====== Size ======
                1) 4 - $5.50
                2) 8 - $7.00
                3) 12 - $8.50 \n
        Choose your sandwich size: """);

        int sizeChoice = scanner.nextInt();
        scanner.nextLine();

        String size;
        double basePrice;

        switch (sizeChoice){
            case 1 -> {size = "4"; basePrice = 5.50;}
            case 2 -> {size = "8"; basePrice = 7.00;}
            case 3 -> {size = "12"; basePrice = 8.50;}
        }

        // Get the type of bread



        // Get if toasted


        //add sub to cart



        //create Sub add Toppings to sub








    }



    //adding supporting methods such as toppings list to add to sub
}


