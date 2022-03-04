package persistence;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.AccountList;
import model.UserAccount;
import org.json.*;

// referenced from JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccountList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private AccountList parseAccountList(JSONObject jsonObject) {
        //String name = jsonObject.getString("accountList");
        AccountList list = new AccountList();
        addAccounts(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addAccounts(AccountList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accountList");
        for (Object json : jsonArray) {
            JSONObject nextAcc = (JSONObject) json;
            addAccount(list, nextAcc);
        }
    }

    // MODIFIES: w
    // EFFECTS: parses userAccount from JSON object and adds it to accountList
    private void addAccount(AccountList list, JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        list.addAccount(username, password);
    }
}
