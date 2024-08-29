import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();

        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Deposit to Account 1");
            System.out.println("2. Withdraw from Account 1");
            System.out.println("3. Print balance of Account 1");
            System.out.println("4. Transfer from Account 1 to Account 2");
            System.out.println("5. Print balance of Account 2");
            System.out.println("6. Create report of accounts");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount for Account 1: ");
                    int depositAmount = scanner.nextInt();
                    account1.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount for Account 1: ");
                    int withdrawalAmount = scanner.nextInt();
                    account1.withdraw(withdrawalAmount);
                    break;
                case 3:
                    account1.printBalance();
                    break;
                case 4:
                    System.out.print("Enter amount to transfer from Account 1 to Account 2: ");
                    int transferAmount = scanner.nextInt();
                    account1.transfer(account2, transferAmount);
                    break;
                case 5:
                    account2.printBalance();
                    break;
                case 6:
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
                        writer.write("1) " + account1.toString());
                        writer.write("\n");
                        writer.write("2) " + account2.toString());
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                    break;
                case 7:
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
