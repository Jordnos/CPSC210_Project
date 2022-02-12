package ui.menus;

import ui.GameTerminal;
import java.io.IOException;

public class GameMenu extends Menu {
    public static final String COMMAND_GAME_MEMORIZE_SEQUENCE = "1";
    private int newScore;


    public GameMenu() throws IOException, InterruptedException {
        super();
    }

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

    // EFFECTS:  runs GameMemorizeSequence
    private void runGameMemorizeSequence() throws IOException, InterruptedException {
        GameTerminal gameHandler = new GameTerminal();
        newScore = gameHandler.start();
        if (loggedIn && newScore > 0) {
            leaderboardMemorizeSequence.addToLeaderboard(newScore, accLoggedIn);
        }
    }
}
