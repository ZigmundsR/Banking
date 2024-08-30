import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
    private int balance;
    private List<Transaction> transactions;

    public BankAccount() {
        this.balance = 0; // Initialize balance to 0
        this.transactions = new ArrayList<>();
    }

    public BankAccount(int balance) {
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(int amount){
        if (amount > 0) {
            this.balance += amount;
            this.transactions.add(new Transaction("Deposit", amount, new Date()));
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(int amount){
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            this.transactions.add(new Transaction("Withdrawal", amount, new Date()));
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }

    public void printBalance(){
        System.out.println("Current balance:" + this.balance);
    }

    public void transfer(BankAccount targetAccount, int amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount; // Deduct amount from the current account
            targetAccount.balance += amount; // Add amount to the target account
            this.transactions.add(new Transaction("Transfer", amount, new Date()));
            targetAccount.transactions.add(new Transaction("Transfer received", amount, new Date()));
        } else {
            System.out.println("Transfer failed: insufficient balance or invalid amount.");
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("BankAccount: ").append(System.lineSeparator());
        sb.append("balance = ").append(balance).append(System.lineSeparator());
        sb.append("transactions:").append(System.lineSeparator());
        if (!transactions.isEmpty()) {
            for (Transaction transaction : transactions) {
                sb.append(transaction).append(System.lineSeparator());
            }
        } else {
            sb.append("No transactions");
        }
        return sb.toString();
    }
}
