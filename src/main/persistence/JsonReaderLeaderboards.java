package persistence;

import java.io.IOException;
import model.Leaderboard;
import org.json.*;

// referenced from JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderLeaderboards extends JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReaderLeaderboards(String source) {
        super(source);
    }

    // EFFECTS: reads leaderboardList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Leaderboard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeaderboards(jsonObject);
    }

    // EFFECTS: parses Leaderboards from JSON object and returns it
    private Leaderboard parseLeaderboards(JSONObject jsonObject) {
        Leaderboard board = new Leaderboard("gameTitle");
        addLeaderboards(board, jsonObject);
        return board;
    }

    // MODIFIES: list
    // EFFECTS: parses Leaderboards from JSON object and adds the list of leaderboards
    private void addLeaderboards(Leaderboard board, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scores");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addLeaderboard(board, nextScore);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses Leaderboards from JSON object and adds it to accountList
    private void addLeaderboard(Leaderboard board, JSONObject jsonObject) {
        int score = jsonObject.getInt("score");
        String user = jsonObject.getString("username");
        board.addToLeaderboard(score, user);
    }
}
