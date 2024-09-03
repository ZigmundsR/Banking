package org.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="balance")
    private int balance;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @Column(name="bank_account_transactions")
    private List<TransactionHistory> transactions;
    private static Logger logger = LogManager.getLogger(BankAccount.class);

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
            this.transactions.add(new TransactionHistory("Deposit", amount, new Date()));
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(int amount){
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            this.transactions.add(new TransactionHistory("Withdrawal", amount, new Date()));
        } else {
            logger.error("Couldn't withdraw: " + amount + " because balance is: " + this.balance);
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
            this.transactions.add(new TransactionHistory("Transfer", amount, new Date()));
            targetAccount.transactions.add(new TransactionHistory("Transfer received", amount, new Date()));
        } else {
            logger.error("Couldn't transfer: " + amount +"  because balance is: " + this.balance);
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
            for (TransactionHistory transaction : transactions) {
                sb.append(transaction).append(System.lineSeparator());
            }
        } else {
            sb.append("No transactions");
        }
        return sb.toString();
    }

    public int getBalance() {
        return balance;
    }

    public List<TransactionHistory> getTransactions() {
        return transactions;
    }
}
