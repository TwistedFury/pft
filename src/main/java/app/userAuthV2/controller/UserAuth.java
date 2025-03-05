package src.main.java.app.userAuthV2.controller;

import src.main.java.app.FileExample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserAuth extends FileExample {

    public boolean login(ArrayList<String> credentials) {
        if (readFromFile("credentials.txt").contains(credentials)) {
            return true;
        }
        return false;
    }

    public boolean register(String username, String password) {

        ArrayList<String> credentials = new ArrayList<>();
        credentials.add(username);
        credentials.add(password);
        if (readFromFile("credentials.txt").contains(credentials)) {
            return true;
        }
        writeToFile("credentials.txt", credentials);
        return true;
    }

    @Override
    public boolean writeToFile(String fileName, ArrayList<String> credentials) {
        if (credentials == null || credentials.isEmpty()) {
            return false;
        }
        Map<String, String> credentialsMap = new HashMap<>();
        credentialsMap.put("username", credentials.get(0).trim());
        credentialsMap.put("password", credentials.get(1).trim());
        return write(credentials, fileName, credentialsMap, true);
    }

    @Override
    public ArrayList<Map<String, String>> readFromFile(String fileName) {
        ArrayList<Map<String, String>> returnList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parsing username:password
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    Map<String, String> credentials = new HashMap<>();
                    credentials.put("username", parts[0].trim());
                    credentials.put("password", parts[1].trim());
                    returnList.add(credentials);
                }
            }
            System.out.println("Data read from file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return returnList;
    }

}
