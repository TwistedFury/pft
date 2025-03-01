package src.main.java.app.transactions.models;

import java.util.Date;

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
        this.date = new Date();
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
        if (this.date.before(end) && this.date.after(start)) {
            if (this.type == type) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date: " + date + "\n");
        sb.append("Transaction #" +  transactionId + "\n");
        sb.append("Type: " + type + "\n");
        sb.append("User #" +  userId + "\n");
        sb.append("Amount $" +  amount + "\n");
        sb.append("Description: " + description + "\n");
        sb.append("Category: " + category + "\n");
        return sb.toString();
    }

}
