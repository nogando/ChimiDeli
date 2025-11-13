package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        ChimiDeliSimpleGUI gui = new ChimiDeliSimpleGUI();



        System.out.println("Do You Want The \n1) GUI \n2) CLI \n");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        if (choice == 1){
            gui.setVisible(true);
        }
        if( choice == 2){
            userInterface.start();
        }



    }
}