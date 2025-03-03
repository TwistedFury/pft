package src.main.java.app.userAuth.controllers;

import src.main.java.app.userAuth.models.*;

import java.util.ArrayList;

public class AuthController {

    private final ArrayList<User> users = new ArrayList<>();
    private int currentUserIndex = 0;


    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.authenticate(username, password)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(User user) {
        if (!users.contains(user)) {
            users.add(user);
            return true;
        }
        return false;
    }

    public void logout() {
        System.exit(0);
    }

    public User getCurrentUser() {
        return users.get(currentUserIndex);
    }

}
