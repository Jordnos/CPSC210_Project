package ui;

import model.AccountList;

public class AccountMenu extends Menu {
    public static final String COMMAND_LOGIN = "I";
    public static final String COMMAND_LOGOUT = "O";
    public static final String COMMAND_SIGNUP = "S";

    protected void processCommand(String command) {
        if (command.equals(COMMAND_LOGIN)) {
            loginPrompt();
        } else if (command.equals(COMMAND_LOGOUT)) {
            logoutPrompt();
        } else if (command.equals(COMMAND_SIGNUP)) {
            signupPrompt();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    protected void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tI -> login");
        System.out.println("\tO -> logout");
        System.out.println("\tO -> signup");
        System.out.println("\tQ -> quit");
    }

    private void loginPrompt() {
        String userIn;
        String passIn;
        System.out.println("Username: ");
        userIn = input.next();
        System.out.println("Password: ");
        passIn = input.next();

        if (loggedIn) {
            System.out.println("already logged in ");
        } else if (AccountList.loginAccount(userIn, passIn)) {
            loggedIn = true;
            accLoggedIn = userIn;
        }
    }

    private void logoutPrompt() {
        if (!loggedIn) {
            System.out.println("you are not logged in" );
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

            if (AccountList.addAccount(userIn, passIn)) {
                loggedIn = true;
                accLoggedIn = userIn;
                System.out.println("sign up successful, you are now logged in ");
            } else {
                System.out.println("signup failed, username already exists ");
            }
        }
    }
}
