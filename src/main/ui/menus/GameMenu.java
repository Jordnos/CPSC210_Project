package ui.menus;

import ui.GameTerminalMemorizeSequence;
import java.io.IOException;

// a menu to select games from
public class GameMenu extends Menu {
    public static final String COMMAND_GAME_MEMORIZE_SEQUENCE = "1";
    private int newScore;

    // EFFECTS:  starts the runMenu
    public GameMenu() throws IOException, InterruptedException {
        super();
    }

    // MODIFIES: this
    // EFFECTS:  runs the command and calls the correlating method
    protected void processCommand(String command) throws IOException, InterruptedException {
        if (command.equals(COMMAND_GAME_MEMORIZE_SEQUENCE)) {
            runGameMemorizeSequence();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS:  displays the menu
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("Select Game from:");
        System.out.println("\t1 -> Memorize Sequence");
        System.out.println("\tQ -> back");
    }

    // MODIFIES: this
    // EFFECTS:  runs GameMemorizeSequence
    private void runGameMemorizeSequence() throws IOException, InterruptedException {
        GameTerminalMemorizeSequence gameHandler = new GameTerminalMemorizeSequence();
        newScore = gameHandler.start();
        if (loggedIn && newScore > 0) {
            leaderboardMemorizeSequence.addToLeaderboard(newScore, accLoggedIn);
        }
    }
}
