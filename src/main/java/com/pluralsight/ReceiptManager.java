package com.pluralsight;

// Saves receipts to a text file

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {

    // Folder where all receipts will be saved
    private static final String RECEIPTS_FOLDER = "receipts";

    // Shared date time format
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");

    // Creates the receipts folder if it does not exist
    private static void createReceiptFolderIfNeeded() {
        File folder = new File(RECEIPTS_FOLDER);

        if (!folder.exists()) {
            boolean created = folder.mkdir();
            if (created) {
                System.out.println("Created receipts folder.");
            }
        }
    }

    // Saves the current cart as a receipt file
    public static void saveReceipt(Cart cart) {
        // Make sure the folder exists
        createReceiptFolderIfNeeded();

        // Use timestamp as a simple order id
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "order_" + timeStamp + ".txt";
        String filePath = RECEIPTS_FOLDER + File.separator + fileName;

        // Try writing the receipt to a file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            writeReceiptContent(bw, cart, timeStamp);
            System.out.println("Receipt saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }

    // Writes the full receipt to the file
    private static void writeReceiptContent(BufferedWriter bw, Cart cart, String orderId) throws IOException {
        writeHeader(bw);
        writeOrderInfo(bw, orderId);
        writeItemsSection(bw, cart);
        writeFooter(bw, cart);
    }

    // ===== Receipt sections =====

    private static void writeHeader(BufferedWriter bw) throws IOException {
        bw.write("=====================================\n");
        bw.write("               CHIMI DELI\n");
        bw.write("           CUSTOMER RECEIPT\n");
        bw.write("=====================================\n\n");
    }

    private static void writeOrderInfo(BufferedWriter bw, String orderId) throws IOException {
        bw.write("Order ID:  " + orderId + "\n");
        bw.write("Date/Time: " + LocalDateTime.now().format(DATE_TIME_FORMATTER) + "\n");
        bw.write("-------------------------------------\n\n");
    }

    private static void writeItemsSection(BufferedWriter bw, Cart cart) throws IOException {
        bw.write("-------------------------------------\n");
        bw.write("ITEMS ORDERED:\n");
        bw.write("-------------------------------------\n\n");

        // Sandwiches
        for (Sandwich sandwich : cart.getSandwiches()) {
            writeSandwichDetails(bw, sandwich);
            bw.write("\n");
        }

        // Drinks
        for (Drink drink : cart.getDrinks()) {
            writeDrinkDetails(bw, drink);
            bw.write("\n");
        }

        // Chips
        for (Chips chips : cart.getChips()) {
            writeChipsDetails(bw, chips);
            bw.write("\n");
        }
    }

    private static void writeFooter(BufferedWriter bw, Cart cart) throws IOException {
        bw.write("-------------------------------------\n");
        bw.write(String.format("Total: $%.2f%n", cart.getTotal()));
        bw.write("Thank you for choosing ChimiDeli!\n");
        bw.write("=====================================\n");
    }

    // ===== Item specific writers =====

    // Writes sandwich details to receipt
    private static void writeSandwichDetails(BufferedWriter bw, Sandwich sandwich) throws IOException {
        bw.write("SANDWICH (" + sandwich.getSize() + "\")\n");
        bw.write("  Bread:   " + sandwich.getBread() + "\n");
        bw.write("  Toasted: " + (sandwich.isToasted() ? "Yes" : "No") + "\n");

        // Write toppings in format
        if (!sandwich.getToppings().isEmpty()) {
            bw.write("  Toppings:\n");
            for (Topping topping : sandwich.getToppings()) {
                bw.write("    - " + topping.getName());
                bw.write("\n");
            }
        }

        // Uses getPrice since your Cart uses getPrice()
        bw.write(String.format("  Price: $%.2f%n", sandwich.getPrice()));
    }

    // Writes drink details to receipt
    private static void writeDrinkDetails(BufferedWriter bw, Drink drink) throws IOException {
        bw.write("DRINK\n");
        // Adjust these getters if your Drink class uses different names
        bw.write("  Size:   " + drink.getSize() + "\n");
        bw.write("  Flavor: " + drink.getFlavor() + "\n");
        bw.write(String.format("  Price: $%.2f%n", drink.getPrice()));
    }

    // Writes chip details to receipt
    private static void writeChipsDetails(BufferedWriter bw, Chips chips) throws IOException {
        bw.write("CHIPS\n");
        bw.write("  Flavor: " + chips.getType() + "\n");
        bw.write(String.format("  Price: $%.2f%n", chips.getPrice()));
    }
}

