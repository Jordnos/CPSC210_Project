package ui.menus;

import model.AccountList;
import model.Leaderboard;

import java.io.IOException;

public class MainMenu extends Menu {
    public static final String COMMAND_GAME = "G";
    public static final String COMMAND_LEADERBOARD = "L";
    public static final String COMMAND_ACCOUNT = "A";

    public MainMenu() throws IOException, InterruptedException {
        super();
    }

    // EFFECTS:  initializes variables
    @Override
    protected void initialize() {
        super.initialize();
        loggedIn = false;
        accLoggedIn = null;
        list = new AccountList();
        leaderboardMemorizeSequence = new Leaderboard(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE);
    }

    // EFFECTS:  runs the command and calls the correlating method
    protected void processCommand(String command) throws IOException, InterruptedException {
        switch (command) {
            case COMMAND_GAME:
                gameMenu();
                break;
            case COMMAND_LEADERBOARD:
                leaderboardMenu();
                break;
            case COMMAND_ACCOUNT:
                accountMenu();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    // EFFECTS:  displays the menu
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("Select from:");
        System.out.println("\tG -> game selection");
        System.out.println("\tL -> leaderboards");
        System.out.println("\tA -> account manager");
        System.out.println("\tQ -> quit");
    }

    // EFFECTS:  runs the game menu
    private void gameMenu() throws IOException, InterruptedException {
        new GameMenu();
    }

    // EFFECTS:  runs the leaderboard menu
    private void leaderboardMenu() throws IOException, InterruptedException {
        new LeaderboardMenu();
    }

    // EFFECTS:  runs the account menu
    private void accountMenu() throws IOException, InterruptedException {
        new AccountMenu();
    }

}
