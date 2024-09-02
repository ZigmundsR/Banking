import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<BankAccount> bankAccounts;
    private static Logger logger = LogManager.getLogger(Customer.class);

    public Customer() {
        this.bankAccounts = new ArrayList<>();
    }
    public Customer(String name) {
        this.name = name;
        this.bankAccounts = new ArrayList<>();
        logger.info("creating customer");
    }

    public void deposit(int accountID,int amount){
        logger.info("deposited " + amount + " to account: " + accountID);
        bankAccounts.get(accountID).deposit(amount);
    }

    public void withdraw(int accountID,int amount){
        logger.info("Withdrawn " + amount + " to account: " + accountID);
        bankAccounts.get(accountID).withdraw(amount);
    }

    public void transfer(int accountID, int targetID, int amount){
        logger.info("Transfered " + amount + "from: " + accountID + " to: " + targetID);
        bankAccounts.get(accountID).transfer(bankAccounts.get(targetID),amount);
    }

    public void addBankAccount(){
        logger.info("adding bank account to customer");
        BankAccount bankAccount = new BankAccount();
        this.bankAccounts.add(bankAccount);
    }

    public void printAccountsBalance(){
        int i = 1;
        for(BankAccount bankAccount : bankAccounts){
            System.out.print("account: " + i + " ");
            bankAccount.printBalance();
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name: ").append(name).append(System.lineSeparator());
        sb.append("BankAccounts:").append(System.lineSeparator());
        if (!bankAccounts.isEmpty()) {
            int i = 0;
            for (BankAccount bankAccount : bankAccounts) {
                i++;
                sb.append(i + ". ").append(bankAccount).append(System.lineSeparator());
            }
        } else {
            sb.append("No BankAccounts");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
