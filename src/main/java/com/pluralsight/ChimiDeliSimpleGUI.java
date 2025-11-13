package com.pluralsight;

import javax.swing.*;
import java.awt.*;

public class ChimiDeliSimpleGUI extends JFrame {

    // This holds all the items the customer picks
    private Cart cart = new Cart();

    // This is the big text box where we show the cart details
    private JTextArea cartArea;

    // This is the constructor.
    public ChimiDeliSimpleGUI() {
        // Set the window title at the top of the frame
        setTitle("ChimiDeli");

        // Close the program when the user closes the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window in pixels (width, height)
        setSize(600, 400);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Set the background color of the window to white
        getContentPane().setBackground(Color.WHITE);

        // Use BorderLayout so we can place things at NORTH, CENTER,
        setLayout(new BorderLayout());

        // Create a panel that will hold the buttons at the top
        JPanel buttonPanel = new JPanel();

        // Make the panel background white to match the window
        buttonPanel.setBackground(Color.WHITE);

        // Create buttons for each action the user can do
        JButton addSandwichButton = new JButton("Add Sandwich");
        JButton addDrinkButton = new JButton("Add Drink");
        JButton addChipsButton = new JButton("Add Chips");
        JButton checkoutButton = new JButton("Checkout");
        JButton exitButton = new JButton("Exit");

        // Add the buttons to the top panel
        buttonPanel.add(addSandwichButton);
        buttonPanel.add(addDrinkButton);
        buttonPanel.add(addChipsButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(exitButton);

        // Put the button panel at the top of the window
        add(buttonPanel, BorderLayout.NORTH);

        // Create the text area where the cart content will be shown
        cartArea = new JTextArea();

        // Do not let the user type inside this text area
        cartArea.setEditable(false);

        // Use a monospaced font so the text lines up nicely
        cartArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Put the text area inside a scroll pane so it can scroll if the text is long
        add(new JScrollPane(cartArea), BorderLayout.CENTER);

        // Connect each button to its action using lambda expressions
        addSandwichButton.addActionListener(e -> addSandwichGUI());
        addDrinkButton.addActionListener(e -> addDrinkGUI());
        addChipsButton.addActionListener(e -> addChipsGUI());
        checkoutButton.addActionListener(e -> checkoutGUI());
        exitButton.addActionListener(e -> dispose());

        // Show the cart contents when the app starts
        refreshCartView();
    }

    // This method updates the cart text area to show the latest cart contents
    private void refreshCartView() {
        // cart.view() should return a string that describes everything in the cart
        cartArea.setText(cart.view());
    }

    // ===== SANDWICH GUI (very simple) =====
    // This method handles the popups for adding a sandwich using dialogs
    private void addSandwichGUI() {
        try {
            // Step 1: Ask the user what size sandwich they want
            String[] sizeOptions = {"4 - $5.50", "8 - $7.00", "12 - $8.50"};
            int sizeChoice = JOptionPane.showOptionDialog(
                    this,                       // Parent window
                    "Choose sandwich size:",    // Message
                    "Sandwich Size",            // Title of the dialog
                    JOptionPane.DEFAULT_OPTION, // We are providing our own buttons
                    JOptionPane.QUESTION_MESSAGE, // Icon type
                    null,                       // No custom icon
                    sizeOptions,                // The button text options
                    sizeOptions[1]              // Default selected option
            );

            // If the user closes the window instead of picking, stop and exit the method
            if (sizeChoice == JOptionPane.CLOSED_OPTION) {
                return;
            }

            // These will store the chosen size and the base price
            String size;
            double basePrice;

            // Match the chosen index to the real size and price
            switch (sizeChoice) {
                case 0 -> { size = "4"; basePrice = 5.50; }
                case 1 -> { size = "8"; basePrice = 7.00; }
                case 2 -> { size = "12"; basePrice = 8.50; }
                // Default case if something strange happens
                default -> { size = "8"; basePrice = 7.00; }
            }

            // Step 2: Ask for bread type using a dropdown dialog
            String[] breads = {"White", "Wheat", "Rye", "Wrap"};
            String bread = (String) JOptionPane.showInputDialog(
                    this,                     // Parent window
                    "Choose bread:",         // Message
                    "Bread",                 // Title
                    JOptionPane.QUESTION_MESSAGE, // Message type
                    null,                    // No custom icon
                    breads,                  // Options for the user
                    breads[1]                // Default selected item
            );

            // If the user cancels or closes the dialog, stop here
            if (bread == null) {
                return;
            }

            // Step 3: Ask if the sandwich should be toasted with a Yes or No dialog
            int toastedOption = JOptionPane.showConfirmDialog(
                    this,
                    "Toasted?",
                    "Toasted",
                    JOptionPane.YES_NO_OPTION
            );

            // Convert the answer to a boolean variable
            boolean toasted = toastedOption == JOptionPane.YES_OPTION;

            // Create a Sandwich object with the chosen options
            // This version does not handle toppings yet, only basic sandwich data
            Sandwich sandwich = new Sandwich(size, bread, toasted, basePrice);

            // Add the sandwich to the cart
            cart.addSandwich(sandwich);

            // Inform the user that the sandwich was added successfully
            JOptionPane.showMessageDialog(this, "Sandwich added to cart.");

            // Refresh the cart display so the new item appears
            refreshCartView();

        } catch (Exception e) {
            // If anything goes wrong in the try block, show an error message
            JOptionPane.showMessageDialog(this, "Something went wrong while adding sandwich.");
        }
    }

    // ===== DRINK GUI =====
    // This method handles the popups for adding a drink
    private void addDrinkGUI() {
        try {
            // Step 1: Let the user choose the drink size from buttons
            String[] sizeOptions = {"Small - $1.50", "Medium - $2.00", "Large - $2.50"};
            int sizeChoice = JOptionPane.showOptionDialog(
                    this,
                    "Choose drink size:",
                    "Drink Size",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sizeOptions,
                    sizeOptions[1]
            );

            // If the user closes the dialog without picking a size, stop
            if (sizeChoice == JOptionPane.CLOSED_OPTION) {
                return;
            }

            // Convert the index from the dialog into a string size name
            String size = switch (sizeChoice) {
                case 0 -> "Small";
                case 1 -> "Medium";
                case 2 -> "Large";
                // If something weird happens, fall back to Medium
                default -> "Medium";
            };

            // Step 2: Ask what drink flavor they want using a dropdown
            String[] drinks = {"Coke", "Sprite", "Fanta", "Water", "Iced Tea"};
            String flavor = (String) JOptionPane.showInputDialog(
                    this,
                    "Choose drink:",
                    "Drink",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    drinks,
                    drinks[0]
            );

            // If the user cancels, stop the method
            if (flavor == null) {
                return;
            }

            // Step 3: Ask if they want ice or not using Yes or No
            int iceChoice = JOptionPane.showConfirmDialog(
                    this,
                    "Add ice?",
                    "Ice",
                    JOptionPane.YES_NO_OPTION
            );

            // Convert the result to a boolean value
            boolean ice = iceChoice == JOptionPane.YES_OPTION;

            // Build the drink name. If ice is true, we add " w/ Ice" to the name
            Drink drink = new Drink(flavor + (ice ? " w/ Ice" : ""), size);

            // Add the drink to the cart
            cart.addDrink(drink);

            // Tell the user the drink was added
            JOptionPane.showMessageDialog(this, "Drink added to cart.");

            // Update the cart area so the drink shows up
            refreshCartView();

        } catch (Exception e) {
            // Handle any unexpected error
            JOptionPane.showMessageDialog(this, "Something went wrong while adding drink.");
        }
    }

    // ===== CHIPS GUI =====
    // This method handles the popups for adding chips
    private void addChipsGUI() {
        try {
            // List of chip options shown to the user
            String[] chipsOptions = {
                    "Lays Original",
                    "Doritos Nacho Cheese",
                    "Cheetos",
                    "Sun Chips",
                    "BBQ Chips"
            };

            // Ask the user which chips they want using a dropdown
            String type = (String) JOptionPane.showInputDialog(
                    this,
                    "Choose chips:",
                    "Chips",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    chipsOptions,
                    chipsOptions[0]
            );

            // If the user cancels or closes the dialog, stop here
            if (type == null) {
                return;
            }

            // Create a new Chips object with the chosen type
            Chips chips = new Chips(type);

            // Add the chips to the cart
            cart.addChips(chips);

            // Tell the user that the chips were added
            JOptionPane.showMessageDialog(this, "Chips added to cart.");

            // Refresh the cart so the new chips show
            refreshCartView();

        } catch (Exception e) {
            // Show an error if something unexpected happens
            JOptionPane.showMessageDialog(this, "Something went wrong while adding chips.");
        }
    }

    // ===== CHECKOUT GUI =====
    // This method handles checkout, confirming the order and saving a receipt
    private void checkoutGUI() {
        // If there is nothing in the cart, tell the user and stop
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty.");
            return;
        }

        // Show the final cart content and ask the user to confirm the order
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Final cart:\n\n" + cart.view() + "\nPlace order and save receipt?",
                "Checkout",
                JOptionPane.YES_NO_OPTION
        );

        // If the user chooses No, stop and do not place the order
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Save the receipt somewhere, for example in a file, using the ReceiptManager
            ReceiptManager.saveReceipt(cart);

            // Tell the user the order is done
            JOptionPane.showMessageDialog(this, "Order complete. Receipt created.");

            // Reset the cart to a new empty one
            cart = new Cart();

            // Update the cart display to show it is empty now
            refreshCartView();
        } catch (Exception e) {
            // If something fails during save, show an error message
            JOptionPane.showMessageDialog(this, "There was a problem finishing your order.");
        }
    }


}

