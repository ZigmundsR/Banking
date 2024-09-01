import java.util.Date;

public class Transaction {
    private String type;
    private int amount;
    private Date date;

    public Transaction(String type, int amount, Date date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction:" +
                " type=" + type  +
                ", amount= " + amount +
                ", date=" + date;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
