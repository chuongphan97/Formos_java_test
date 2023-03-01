package com.formos.test.view;

import com.formos.test.enums.Product;
import com.formos.test.service.InventoryService;
import com.formos.test.service.InventoryServiceImpl;

import java.util.Scanner;

public class Main {

    private static final InventoryService inventoryService = new InventoryServiceImpl();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equals("4")) {
            printMenu();
            System.out.println("Enter your choice");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("________________INVENTORY________________");
                    inventoryService.listCurrentInventory();
                    System.out.println("_________________________________________");
                    break;
                case "2":
                    System.out.println("Have 3 type of smoothie: ");
                    System.out.println("\t 1. Bananas Smoothie.");
                    System.out.println("\t 2. Mangoes Smoothie.");
                    System.out.println("\t 3. Strawberries Smoothie.");
                    System.out.println("\t 4. Back to menu.");
                    System.out.println("Please enter your choice.");
                    Product product = null;
                    int quantity = 0;
                    String smoothieChoice = "";
                    Scanner sc1 = new Scanner(System.in);
                    smoothieChoice = sc1.nextLine();
                    if (smoothieChoice.equals("4") || (!smoothieChoice.equals("1") && !smoothieChoice.equals("2") && !smoothieChoice.equals("3"))) {
                        break;
                    }
                    if (smoothieChoice.equals("1")) {
                        product = Product.BANANA;
                    }
                    if (smoothieChoice.equals("2")) {
                        product = Product.MANGO;
                    }
                    if (smoothieChoice.equals("3")) {
                        product = Product.STRAW_BERRY;
                    }
                    System.out.println("Please enter quantity: ");
                    Scanner sc2 = new Scanner(System.in);
                    String s = null;
                    System.out.println("enter a number");
                    while (!(s = sc2.nextLine().trim()).matches("\\d+") && Integer.parseInt(s) > 0) {
                        System.out.println("Please enter only integer greater than 0");
                    }
                    quantity = Integer.parseInt(s);
                    inventoryService.sellProduct(product, quantity);

                    System.out.println("_________________________________________");
                    break;
                case "3":
                    System.out.println("________________DAILY_SALES________________");
                    inventoryService.dailySales();
                    System.out.println("_________________________________________");
                    break;
            }
        }


    }

    private static void printMenu() {
        System.out.println("________________SMOOTHIE________________");
        System.out.println("\t 1. See current inventory");
        System.out.println("\t 2. Sell smoothie");
        System.out.println("\t 3. See daily sale");
        System.out.println("\t 4. Exist");
        System.out.println("Please choose the number");
    }
}
