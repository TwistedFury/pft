package src.main.java.app.userAuth.models;

public class User {

    private int userId;
    private String userName;
    private String password;
    private String email;

    public User(int userId, String userName, String password, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        // TODO: Maybe getters/setters for these?
    }

    public boolean authenticate(String username, String password) {
        return this.userName.equals(username) && this.password.equals(password);
    }

    public User register(String username, String password, String email) {
        return new User(userId, username, password, email);
    }

    public User getUserInfo() {
        return this;
    }

    // UML has a updateUserInfo method that takes in a user..
    // At that point, just replace the user.
    // That's a responsibility of the controller

}
