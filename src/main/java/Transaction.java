import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.Date;

public class Transaction {
    private String type;
    private int amount;
    private Date date;
    private static Logger logger = LogManager.getLogger(Transaction.class);

    public Transaction(String type, int amount, Date date) {

        //logger.info(date + " creating transaction: " + type + " amount: " + amount);
        ThreadContext.put("date", date.toString());
        ThreadContext.put("amount", String.valueOf(amount));
        ThreadContext.put("type", type);

        logger.info("creating transaction");

        ThreadContext.clearAll();

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
