package src.main.java.app;

import java.io.*;
import java.util.*;

public class FileExample {

    /* Context doesn't matter to it, if the line doesn't contain the regex, it skips it */

    public static void writeToFile(String fileName, List<String> credentials) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String credential : credentials) {
                writer.write(credential);
                writer.newLine();
            }
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }


    public static List<Map<String, String>> readFromFile(String fileName) {
        List<Map<String, String>> credentialsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parsing username:password
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    Map<String, String> credentials = new HashMap<>();
                    credentials.put("username", parts[0].trim());
                    credentials.put("password", parts[1].trim());
                    credentialsList.add(credentials);
                }
            }
            System.out.println("Data read from file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return credentialsList;
    }



    // Main method to test the functions
    public static void main(String[] args) {
        String fileName = "credentials.txt";

        // Sample username:password pairs
        List<String> credentials = new ArrayList<>();
        credentials.add("user1:password1");
        credentials.add("user2:password2");
        credentials.add("user3:password3");

        // Write to the file
        writeToFile(fileName, credentials); //* Once it is there, this isn't needed

        // Read from the file and parse the data
        List<Map<String, String>> parsedCredentials = readFromFile(fileName);
        for (Map<String, String> credential : parsedCredentials) {
            System.out.println("Username: " + credential.get("username") + ", Password: " + credential.get("password"));
        }
    }
}
