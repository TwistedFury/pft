package src.main.java.app.userAuthV2;

import src.main.java.app.FileExample;

import java.util.ArrayList;

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

}
