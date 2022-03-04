package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.AccountList;
import model.Leaderboard;
import org.json.*;

// referenced from JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderLeaderboards {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderLeaderboards(String source) {
        this.source = source;
    }

    // EFFECTS: reads leaderboardList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Leaderboard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeaderboards(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
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
