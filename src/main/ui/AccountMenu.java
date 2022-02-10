package ui;

import model.UserAccount;

public class AccountMenu extends Menu {
    public static final String COMMAND_LOGIN = "I";
    public static final String COMMAND_LOGOUT = "O";
    public static final String COMMAND_SIGNUP = "S";

    protected void processCommand(String command) {
        switch (command) {
            case COMMAND_LOGIN:
                loginPrompt();
                break;
            case COMMAND_LOGOUT:
                logoutPrompt();
                break;
            case COMMAND_SIGNUP:
                signupPrompt();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    protected void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tI -> login");
        System.out.println("\tO -> logout");
        System.out.println("\tS -> signup");
        System.out.println("\tQ -> back");
    }

    private void loginPrompt() {
        if (loggedIn) {
            System.out.println("already logged in ");
        } else {
            String userIn;
            String passIn;
            System.out.println("Username: ");
            userIn = input.next();
            System.out.println("Password: ");
            passIn = input.next();

            if (UserAccount.loginAccount(userIn, passIn)) {
                loggedIn = true;
                accLoggedIn = userIn;
                System.out.println("login successful ");
            } else {
                System.out.println("login failed, wrong username or password ");
            }
        }
    }

    private void logoutPrompt() {
        if (!loggedIn) {
            System.out.println("you are not logged in ");
        } else {
            loggedIn = false;
            accLoggedIn = null;
            System.out.println("log out successful ");
        }
    }

    private void signupPrompt() {
        if (loggedIn) {
            System.out.println("already logged in ");
        } else {
            String userIn;
            String passIn;
            System.out.println("Username: ");
            userIn = input.next();
            System.out.println("Password: ");
            passIn = input.next();

            if (UserAccount.addAccount(userIn, passIn)) {
                loggedIn = true;
                accLoggedIn = userIn;
                System.out.println("sign up successful, you are now logged in ");
            } else {
                System.out.println("signup failed, username already exists ");
            }
        }
    }
}
