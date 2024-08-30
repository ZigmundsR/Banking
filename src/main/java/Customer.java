import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<BankAccount> bankAccounts;

    public Customer() {
        this.bankAccounts = new ArrayList<>();
    }
    public Customer(String name) {
        this.name = name;
        this.bankAccounts = new ArrayList<>();
    }

    public void deposit(int accountID,int amount){
        bankAccounts.get(accountID).deposit(amount);
    }

    public void withdraw(int accountID,int amount){
        bankAccounts.get(accountID).withdraw(amount);
    }

    public void transfer(int accountID, int targetID, int amount){
        bankAccounts.get(accountID).transfer(bankAccounts.get(targetID - 1),amount);
    }

    public void addBankAccount(){
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
}
