package persistence;

import java.io.IOException;

import model.AccountList;
import org.json.*;

// referenced from JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderAccounts extends JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReaderAccounts(String source) {
        super(source);
    }

    // EFFECTS: reads accountLiset from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccountList(jsonObject);
    }

    // EFFECTS: parses accountList from JSON object and returns it
    private AccountList parseAccountList(JSONObject jsonObject) {
        AccountList list = new AccountList();
        addAccounts(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses UserAccounts from JSON object and adds them to workroom
    private void addAccounts(AccountList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accountList");
        for (Object json : jsonArray) {
            JSONObject nextAcc = (JSONObject) json;
            addAccount(list, nextAcc);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses userAccount from JSON object and adds it to accountList
    private void addAccount(AccountList list, JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        list.addAccount(username, password);
    }

}
