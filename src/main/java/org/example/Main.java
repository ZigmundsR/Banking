package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Customer customer = new Customer("Zigmunds");
        customer.addBankAccount();

        int accountID = 0;

        boolean running = true;

        while (running) {
            System.out.println("You are working on bank account: " + (accountID + 1));
            System.out.println("Choose an option:");
            System.out.println("1. Switch account");
            System.out.println("2. Withdraw from Account");
            System.out.println("3. Deposit to Account");
            System.out.println("4. Print balance of Accounts");
            System.out.println("5. Transfer from Account to Account");
            System.out.println("6. Create a bank account");
            System.out.println("7. Create report of customer");
            System.out.println("8. Exit");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine(); // Clear the invalid input from the scanner
                logger.error("incorrect input (choice): ", e);
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");

                    try {
                        int tempAccountID = scanner.nextInt();
                        if (customer.getBankAccounts().size() >= tempAccountID && tempAccountID > 0) {
                            logger.info("Switched accounts from: " + tempAccountID + " to " + accountID);
                            accountID = tempAccountID;
                        }
                    } catch (Exception e) {
                        System.out.println("incorrect input");
                        scanner.nextLine(); // Clear the invalid input from the scanner
                        logger.error("incorrect input (switch): ", e);
                    }
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount for Account: ");
                    int withdrawalAmount = scanner.nextInt();
                    customer.withdraw(accountID, withdrawalAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount for Account: ");
                    int depositAmount = scanner.nextInt();
                    customer.deposit(accountID, depositAmount);
                    break;
                case 4:
                    customer.printAccountsBalance();
                    break;
                case 5:
                    System.out.print("Enter amount to transfer from Account to Account: ");
                    int transferAmount = scanner.nextInt();
                    System.out.print("Enter which account to send money to: ");
                    int targetID = scanner.nextInt() - 1;
                    customer.transfer(accountID, targetID, transferAmount);
                    break;
                case 6:
                    customer.addBankAccount();
                    break;
                case 7:
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
                        logger.info("Created report to a file");
                        writer.write(customer.toString());
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                        logger.error("Couldn't create report to a file");
                    } finally {
                        System.out.println("finally block worked :)");
                    }
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
        scanner.close();
        System.out.println("Program terminated.");
    }
}
