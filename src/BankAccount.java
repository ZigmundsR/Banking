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

    public void printBalance(int amount){
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
}
