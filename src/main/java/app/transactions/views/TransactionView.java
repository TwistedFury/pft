package src.main.java.app.transactions.views;

import src.main.java.app.transactions.models.Transaction;

import java.util.ArrayList;

public class TransactionView {
    /**
     * Display what the user will need to fill out for a transaction
     */
    public void displayTransactionForm() {
        System.out.println("""
                Amount: In USD , no need for '$'
                Description
                Category (Think purpose)
                Transaction Type (INCOME / EXPENSE)""");
    }

    /**
     * Calls {@code System.out.println()} on all {@code Transaction}s in list
     * @param transactions {@code Transaction}s to print
     */
    public void displayTransactionList(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction + "\n");
        }
    }

    /**
     * Calls {@code System.out.println()} on a given {@code Transaction}
     * @param transaction {@code transaction} to show to user
     */
    public void displayTransactionDetails(Transaction transaction) {
        System.out.println(transaction + "\n");
    }

    /**
     * Prints success message to user
     * @param message what do you want to tell the user about their success?
     */
    public void showTransactionSuccess(String message) {
        System.out.println(message);
    }

    /**
     * Prints error message to user
     * @param message what do you want to tell the about the error that they caused?
     */
    public void showTransactionError(String message) {
        System.out.println(message);
    }

}
