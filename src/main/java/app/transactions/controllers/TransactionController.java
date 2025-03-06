package src.main.java.app.transactions.controllers;

import src.main.java.app.FileExample;
import src.main.java.app.transactions.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* TODO: CAREFUL WHILE TESTING, DATE IS SET TO SYSTEM TIME */

public class TransactionController extends FileExample {

    private final ArrayList<Transaction> transactions = new ArrayList<>();

    /* This controller uses file 'transactions.txt' */

    /**
     * Add transaction to file
     * @param transaction transaction to add
     * @return boolean (worked or no?)
     */
    public boolean addTransaction(Transaction transaction) {
        return writeToFile("transactions.txt", transaction.getInfo());
    }

    /**
     * Get all transactions related to a certain {@code userId}
     * @param userId {@code User} to get {@code transactions} for
     * @return {@code ArrayList<>} of all {@code User} {@code Transactions}
     */
    public ArrayList<Map<String, String>> getTransactions(int userId) {
        return readFromFile("transactions.txt"); // TODO: Need to add userId check
    }

    /**
     * Get a certain {@code transaction} by its {@code Id}
     * @param transactionId {@code Id} of the {@code transaction} you are searching for
     * @return {@code Transaction} (If found), or {@code null}
     */
    public Map<String, String> getTransactionById(int transactionId) {
        ArrayList<Map<String, String>> transactions = readFromFile("transactions.txt");
        for (Map<String, String> transaction : transactions) {
            if (Integer.parseInt(transaction.get("id")) == transactionId) {
                return transaction;
            }
        }
        return null;
    }

    /**
     * Update a {@code transaction} to a {@code new transaction}
     * @param transactionToSet what you are going to set it to
     * @param transactionId {@code Id} of {@code transaction} we are replacing
     * @return {@code boolean} {@code True} if completed, {@code False} if it failed
     */
    public boolean updateTransaction(Map<String, String> transactionToSet, int transactionId) {
        ArrayList<Map<String, String>> transactions = readFromFile("transactions.txt");
        ArrayList<Map<String, String>> newTransactions = new ArrayList<>();
        for (Map<String, String> transaction : transactions) {
            if (Integer.parseInt(transaction.get("id")) != transactionId) {
                newTransactions.add(transaction);
            } else if (Integer.parseInt(transaction.get("id")) == transactionId) {
                newTransactions.add(transactionToSet);
            }
        }
        return writeToFileSpecific(transactions);
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

    @Override
    public boolean writeToFile(String fileName, ArrayList<String> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return false;
        }
        Map<String, String> transactionsMap = new HashMap<>();
        transactionsMap.put("id", transactions.get(0).trim());
        transactionsMap.put("amount", transactions.get(1).trim());
        transactionsMap.put("description", transactions.get(2).trim());
        transactionsMap.put("category", transactions.get(3).trim());
        transactionsMap.put("date", transactions.get(4).trim());
        transactionsMap.put("type", transactions.get(5).trim());
        return write(transactions, fileName, transactionsMap, true);
    }

    @Override
    public ArrayList<Map<String, String>> readFromFile(String fileName) {
        ArrayList<Map<String, String>> returnList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parsing username:password
                String[] parts = line.split(":");
                if (parts.length == 6) { // Length for transactions
                    Map<String, String> transactions = new HashMap<>();
                    transactions.put("id", parts[0].trim());
                    transactions.put("amount", parts[1].trim());
                    transactions.put("description", parts[2].trim());
                    transactions.put("category", parts[3].trim());
                    transactions.put("date", parts[4].trim());
                    transactions.put("type", parts[5].trim());
                    returnList.add(transactions);
                }
            }
            System.out.println("Data read from file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
        return returnList;
    }

    private boolean writeToFileSpecific(ArrayList<Map<String, String>> transactions) {
        ArrayList<String> toWrite = new ArrayList<>();
        for (Map<String, String> transaction : transactions) {
            toWrite.add(transaction.get("id"));
            toWrite.add(transaction.get("amount"));
            toWrite.add(transaction.get("description"));
            toWrite.add(transaction.get("category"));
            toWrite.add(transaction.get("date"));
            toWrite.add(transaction.get("type"));
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"));
            for (String transaction : toWrite) {
                writer.write(transaction);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static void main(String[] args) {
//        TransactionController transactionController = new TransactionController();
//        transactionController.addTransaction(new Transaction(1, 100, "null", "null", TransactionType.INCOME));
//        for (Map<String, String> item :  transactionController.readFromFile("transactions.txt")) {
//            System.out.println(item.get("id"));
//            System.out.println(item.get("amount"));
//            System.out.println(item.get("description"));
//            System.out.println(item.get("category"));
//            System.out.println(item.get("date"));
//            System.out.println(item.get("type"));
//        }
//    }

}
