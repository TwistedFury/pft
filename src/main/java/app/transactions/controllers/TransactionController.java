package src.main.java.app.transactions.controllers;

import src.main.java.app.transactions.models.*;
import src.main.java.app.transactions.views.*;

import java.util.ArrayList;
import java.util.Date;

public class TransactionController {

    private final ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * Add a {@code Transaction} to the ArrayList {@code transactions}
     * @param transaction {@code Transaction} to add
     * @return {@code boolean} True, added successfully, False, unable to add
     */
    public boolean addTransaction(Transaction transaction) {
        if (transactions.contains(transaction)) {
            return false;
        }
        transactions.add(transaction);
        return true;
    }

    /**
     * Get all transactions related to a certain {@code userId}
     * @param userId {@code User} to get {@code transactions} for
     * @return {@code ArrayList<>} of all {@code User} {@code Transactions}
     */
    public ArrayList<Transaction> getTransactions(int userId) {
        ArrayList<Transaction> transactions2 = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionByUId(userId) != null) {
                transactions2.add(transaction);
            }
        }
        return transactions2;
    }

    /**
     * Get a certain {@code transaction} by its {@code Id}
     * @param transactionId {@code Id} of the {@code transaction} you are searching for
     * @return {@code Transaction} (If found), or {@code null}
     */
    public Transaction getTransactionById(int transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        return null;
    }

    /**
     * Update a {@code transaction} to a {@code new transaction}
     * @param transaction what you are going to set it to
     * @param transactionId {@code Id} of {@code transaction} we are replacing
     * @return {@code boolean} {@code True} if completed, {@code False} if it failed
     */
    public boolean updateTransaction(Transaction transaction, int transactionId) {
        for (Transaction transaction1 : transactions) {
            if (transaction1.getTransactionId() == transactionId) {
                transactions.set(transactions.indexOf(transaction1), transaction);
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a {@code transaction} by its {@code Id}
     * @param transactionId {@code Id} of what we are looking to delete
     * @return {@code boolean} {@code True} if completed, {@code False} if it failed
     */
    public boolean deleteTransaction(int transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId() == transactionId) {
                transactions.remove(transaction);
                return true;
            }
        }
        return false;
    }

    /**
     * Get an {@code ArrayList<>} of {@code Transaction}s fitting a filter
     * @param start start of filter ({@code Date})
     * @param end end of filter ({@code Date})
     * @param type {@code TransactionType} for filter
     * @return {@code ArrayList<>} of {@code Transaction}s
     */
    public ArrayList<Transaction> getFilteredTransactions(Date start, Date end, TransactionType type) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.checkFilter(start, end, type)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

}
