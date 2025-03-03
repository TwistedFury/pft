package src.main.java.app.userAuthV2;

import src.main.java.app.FileExample;

import java.util.Map;

public class UserAuth extends FileExample {

    public boolean login(Map<String, String> credentials) {
        if (readFromFile("credentials.txt").contains(credentials)) {
            return true;
        }
        return false;
    }

}
