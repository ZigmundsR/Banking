import java.util.Scanner;

public class BankAccount {
    private int balance;

    public BankAccount() {
        this.balance = 0; // Initialize balance to 0
    }

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount){
        if (amount > 0) {
            this.balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(int amount){
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }

    public void printBalance(){
        System.out.println("Current balance:" + this.balance);
    }

    public void transfer(BankAccount targetAccount, int amount) {
        if (amount > 0 && amount <= this.balance) {
            this.withdraw(amount); // Deduct amount from the current account
            targetAccount.deposit(amount); // Add amount to the target account
            System.out.println("Transferred " + amount + " to the target account.");
        } else {
            System.out.println("Transfer failed: insufficient balance or invalid amount.");
        }
    }

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
            System.out.println("6. Exit");
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
