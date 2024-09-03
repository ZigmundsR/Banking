package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.example.converter.DateConverter;

import java.util.Date;

@Entity
@Table(name="transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="type")
    private String type;
    @Column(name="amount")
    private int amount;
    @Column(name="date")
    @Convert(converter = DateConverter.class)
    private Date date;

    private static Logger logger = LogManager.getLogger(TransactionHistory.class);

    public TransactionHistory() {
    }

    public TransactionHistory(String type, int amount, Date date) {

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
