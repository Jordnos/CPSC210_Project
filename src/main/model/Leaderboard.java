package model;


import java.util.ArrayList;
import java.util.HashMap;

public class Leaderboard {
    private String gameTitle;
    private HashMap<GameScore,ArrayList<String>> scoresList;

    public Leaderboard(String gameTitle) {
        this.gameTitle = gameTitle;
        scoresList = new HashMap<>();
    }

    // EFFECTS:  returns the scoreList
    public HashMap<GameScore,ArrayList<String>> getScoresList() {
        return scoresList;
    }

    // MODIFIES: this
    // EFFECTS:  adds score to leaderboards with associated user if the score isn't already on
    public void addToLeaderboard(int score, String user) {
        GameScore s = new GameScore(score);
        ArrayList<String> arr;
        if (scoresList.containsKey(s)) {
            arr = scoresList.get(s);
        } else {
            arr = new ArrayList<>();
        }
        if (!arr.contains(user)) {
            arr.add(user);
            scoresList.put(s,arr);
        }
    }

    // MODIFIES: this
    // EFFECTS:  for user, delete all other scores that aren't the highest score
    public void keepHighScoreOnly(String user) {
        ArrayList<String> arr;
        int highScore = 0;
        for (HashMap.Entry<GameScore,ArrayList<String>> entry: scoresList.entrySet()) {
            arr = entry.getValue();
            if (arr.contains(user) && highScore < entry.getKey().getScore()) {
                highScore = entry.getKey().getScore();
                arr.remove(user);
            }
        }
    }
}
