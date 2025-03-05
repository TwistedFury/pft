package src.main.java.app.transactions.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Transaction {

    private int transactionId, userId;
    private double amount;
    private String description, category;
    private Date date;
    private TransactionType type;

    private boolean createTransaction(int transactionId, int userId, double amount, String description, String category, TransactionType type) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = new GregorianCalendar().getTime();
        this.type = type;
        return true; // TODO: Maybe getters/setters for these?
    }

    public Transaction(int userId, double amount, String description, String category, TransactionType type) {
        createTransaction(transactionId, userId, amount, description, category, type);
    }

    public Transaction getTransactionByUId(int userId) {
        if (userId == this.userId) {
            return this;
        }
        return null;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public boolean checkFilter(Date start, Date end, TransactionType type) {
        if (date.before(end) && date.after(start)) {
            return this.type == type;
        }
        return false;
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(String.valueOf(transactionId));
        info.add(String.valueOf(amount));
        info.add(String.valueOf(description));
        info.add(String.valueOf(category));
        info.add(String.valueOf(date.getDate() +  "-" + (date.getMonth() + 1) + "-" + (date.getYear() + 1900)));
        info.add(String.valueOf(type));
        return info;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date: ").append(date).append("\n");
        sb.append("Transaction #").append(transactionId).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("User #").append(userId).append("\n");
        sb.append("Amount $").append(amount).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Category: ").append(category).append("\n");
        return sb.toString();
    }

}
