package src.main.java.app.userAuthV2.views;

public class LoginView {

    public void displayLoginForm() {
        System.out.println("""
                Username: example
                Password: passW0rd""");
    }

    public void displayRegisterForm() {
        System.out.println("""
                Username: example
                Password: passW0rd
                Email: example@gmail.com""");
    }

    public void showLoginError(String message) {
        System.out.println(message);
    }

    public void showRegistrationSuccess(String message) {
        System.out.println(message);
    }

}
