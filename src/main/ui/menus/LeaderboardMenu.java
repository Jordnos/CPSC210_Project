package ui.menus;

import model.Leaderboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LeaderboardMenu extends Menu {
    public static final String COMMAND_GAME_MEMORIZE_SEQUENCE = "1";
    public static final int HIGHEST_SCORE = 50; // temp holder for highest score on leaderboards

    public LeaderboardMenu() throws IOException, InterruptedException {
        super();
    }

    // EFFECTS:  runs the command and calls the correlating method
    protected void processCommand(String command) {
        if (command.equals(COMMAND_GAME_MEMORIZE_SEQUENCE)) {
            showLeaderBoardMemorizeSequence(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS:  displays the menu
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("Select Leaderboard from:");
        System.out.println("\t1 -> Memorize Sequence");
        System.out.println("\tQ -> back");
    }

    // EFFECTS:  displays leaderboard for Memorize Sequence game
    private void showLeaderBoardMemorizeSequence(String game) {
        HashMap<Integer,ArrayList<String>> list = leaderboardMemorizeSequence.getScoresList();
        System.out.println("SCORES\tUSER");

        for (int i = HIGHEST_SCORE; i > 0; i--) {
            if (list.containsKey(i)) {
                for (String user: list.get(i)) {
                    System.out.println(i + "\t\t" + user);
                }
            }
        }
    }

}
