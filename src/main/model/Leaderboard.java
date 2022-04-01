package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// this class has methods needed to create a leaderboard for any game
public class Leaderboard implements Writable {
    public static final String LEADERBOARD_GAME_MEMORIZE_SEQUENCE = "MEMORIZE SEQUENCE";

    private String gameTitle;
    private HashMap<String, Integer> scoresList;

    public Leaderboard(String gameTitle) {
        this.gameTitle = gameTitle;
        scoresList = new HashMap<>();
    }

    // EFFECTS:  returns the scoreList
    public Map<String, Integer> getScoresList() {
        return Collections.unmodifiableMap(scoresList);
    }

    // MODIFIES: this
    // EFFECTS:  adds score to leaderboards with associated user if the score isn't already on,
    //           only record the highest score per user, deletes old score if new score is higher
    public void addToLeaderboard(int score, String user) {
        if (scoresList.containsKey(user)) {
            if (scoresList.get(user) < score) {
                scoresList.replace(user, score);
            }
        } else {
            scoresList.put(user, score);
        }
    }

    public String getGameTitle() {
        return gameTitle;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", gameTitle);
        json.put("scores", leaderboardToJson());
        return json;
    }

    // EFFECTS: returns scores in this leaderboard as a JSON array
    private JSONArray leaderboardToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<String,Integer> entry : scoresList.entrySet()) {
            jsonArray.put(scoreToJson(entry));
        }

        return jsonArray;
    }

    public JSONObject scoreToJson(Map.Entry<String,Integer> entry) {
        JSONObject json = new JSONObject();
        json.put("username", entry.getKey());
        json.put("score", entry.getValue());
        return json;
    }

}
