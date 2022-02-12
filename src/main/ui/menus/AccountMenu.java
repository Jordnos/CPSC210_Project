package ui.menus;

import ui.menus.Menu;

import java.io.IOException;

public class AccountMenu extends Menu {
    public static final String COMMAND_LOGIN = "I";
    public static final String COMMAND_LOGOUT = "O";
    public static final String COMMAND_SIGNUP = "S";

    public AccountMenu() throws IOException, InterruptedException {
        super();
    }

    // EFFECTS:  runs the command and calls the correlating method
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

    // EFFECTS:  displays the menu
    protected void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tI -> login");
        System.out.println("\tO -> logout");
        System.out.println("\tS -> signup");
        System.out.println("\tQ -> back");
    }

    // REQUIRES: username and password are not empty
    // EFFECTS:  prompts user for login info, either logging is successful or fails, or user is
    //           already logged in; prints appropriate message
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

            if (list.loginAccount(userIn, passIn)) {
                loggedIn = true;
                accLoggedIn = userIn;
                System.out.println("login successful ");
            } else {
                System.out.println("login failed, wrong username or password ");
            }
        }
    }

    // EFFECTS:  logs user off or user isn't even logged in; prints appropriate message
    private void logoutPrompt() {
        if (!loggedIn) {
            System.out.println("you are not logged in ");
        } else {
            loggedIn = false;
            accLoggedIn = null;
            System.out.println("log out successful ");
        }
    }

    // REQUIRES: username and password are not empty
    // EFFECTS:  prompts user for signup info, either signup is successful or fails because
    //           of unique usernames, or user is already logged in; prints appropriate message
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

            if (list.addAccount(userIn, passIn)) {
                loggedIn = true;
                accLoggedIn = userIn;
                System.out.println("sign up successful, you are now logged in ");
            } else {
                System.out.println("signup failed, username already exists ");
            }
        }
    }
}
