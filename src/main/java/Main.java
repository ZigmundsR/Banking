import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Customer customer = new Customer("Zigmunds");
        customer.addBankAccount();

        int accountID = 0;

        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Switch account");
            System.out.println("2. Withdraw from Account");
            System.out.println("3. Deposit to Account");
            System.out.println("4. Print balance of Accounts");
            System.out.println("5. Transfer from Account to Account");
            System.out.println("6. Create an account");
            System.out.println("7. Create report of customer");
            System.out.println("8. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    accountID = scanner.nextInt() - 1;
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
                        writer.write(customer.toString());
                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
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
