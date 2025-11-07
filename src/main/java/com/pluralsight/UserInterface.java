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

        String size = " ";
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

        String toppingName = " ";
        String toppingType;
        double meatToppingPrice = 0.10;
        double cheeseToppingPrice = 0.10;

        if (size.equalsIgnoreCase("4")){

        System.out.println("""
        \n====== Meats ======
        1) Chicken
        2) Steak
        3) Ham
        4) Salami
        5) Roast beef
        6) Bacon
        7) No Meat
        Choose your protein: """);

        int meatChoice = scanner.nextInt();
        scanner.nextLine();

        switch (meatChoice){
            case 1 -> {toppingName = "Chicken"; toppingType = "Meat";}
            case 2 -> {toppingName = "Steak"; toppingType = "Meat";}
            case 3 -> {toppingName = "Ham"; toppingType = "Meat";}
            case 4 ->{toppingName = "Salami"; toppingType = "Meat";}
            case 5 ->{toppingName = "Roast Beef"; toppingType = "Meat";}
            case 6 ->{toppingName = "Bacon"; toppingType = "Meat";}
            case 7 -> {
                System.out.println("No Meat");
            }
        }
            meatToppingPrice = 1.00;

            System.out.println("Want Extra " +  toppingName + """
                    ?
                    \n
                    1) Yes
                    2) No
                    """);

            int extraMeat = scanner.nextInt();
            scanner.nextLine();

            if(extraMeat == 1) { meatToppingPrice += .50;}




       System.out.println("""
        \n====== Cheese ======
        1) American
        2) Provolone
        3) Cheddar
        4) Swiss
        Choose your cheese: """);

            int cheeseChoice = scanner.nextInt();
            scanner.nextLine();

            switch (cheeseChoice){
                case 1 -> {toppingName = "American"; toppingType = "Cheese";}
                case 2 -> {toppingName = "Steak"; toppingType = "Meat";}
                case 3 -> {toppingName = "Ham"; toppingType = "Meat";}
                case 4 ->{toppingName = "Salami"; toppingType = "Meat";}
            }
            meatToppingPrice = 1.00;

            System.out.println("Want Extra " +  toppingName + """
                    ?
                    \n
                    1) Yes
                    2) No
                    """);

            int extraCheese = scanner.nextInt();
            scanner.nextLine();

            if(extraMeat == 1) { meatToppingPrice += .50;}











        } else if (size.equalsIgnoreCase("8"))

        System.out.println("""
        \n====== Meats ======
        1) Chicken
        2) Steak
        3) Ham
        4) Salami
        5) Roast beef
        6) Bacon
        Choose your meat: """);

            int meatChoice = scanner.nextInt();
            scanner.nextLine();

            switch (meatChoice){
                case 1 -> {toppingName = "Chicken"; toppingType = "Meat";}
                case 2 -> {toppingName = "Steak"; toppingType = "Meat";}
                case 3 -> {toppingName = "Ham"; toppingType = "Meat";}
                case 4 ->{toppingName = "Salami"; toppingType = "Meat";}
                case 5 ->{toppingName = "Roast Beef"; toppingType = "Meat";}
                case 6 ->{toppingName = "Bacon"; toppingType = "Meat";}
            }
            meatToppingPrice = 2.00;






        }












    }



    //adding supporting methods such as toppings list to add to sub





