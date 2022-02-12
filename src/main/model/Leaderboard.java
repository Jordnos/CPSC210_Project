package model;


import java.util.ArrayList;
import java.util.HashMap;

// this class has methods needed to create a leaderboard for any game
public class Leaderboard {
    public static final String LEADERBOARD_GAME_MEMORIZE_SEQUENCE = "MEMORIZE SEQUENCE";

    private String gameTitle;
    private HashMap<Integer,ArrayList<String>> scoresList;

    public Leaderboard(String gameTitle) {
        this.gameTitle = gameTitle;
        scoresList = new HashMap<>();
    }

    // EFFECTS:  returns the scoreList
    public HashMap<Integer,ArrayList<String>> getScoresList() {
        return scoresList;
    }

    // MODIFIES: this
    // EFFECTS:  adds score to leaderboards with associated user if the score isn't already on
    public void addToLeaderboard(int score, String user) {
        ArrayList<String> arr;
        if (scoresList.containsKey(score)) {
            arr = scoresList.get(score);
        } else {
            arr = new ArrayList<>();
        }
        if (!arr.contains(user)) {
            arr.add(user);
            scoresList.put(score,arr);
        }
    }

    public String getGameTitle() {
        return gameTitle;
    }

}
