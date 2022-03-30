package ui;

import model.AccountList;
import model.Leaderboard;
import persistence.JsonReaderAccounts;
import persistence.JsonReaderLeaderboards;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// this class manages the persistence of the application and it's UI
public class SaveLoadManagerUI {
    public static final String JSON_STORE_ACCOUNTS = "./data/accountList.json";
    public static final String JSON_STORE_LEADERBOARDS = "./data/leaderboards.json";

    protected static JsonWriter jsonWriterAccounts;
    protected static JsonWriter jsonWriterLeaderboards;
    protected static JsonReaderAccounts jsonReaderAccounts;
    protected static JsonReaderLeaderboards jsonReaderLeaderboards;

    private AccountList accList;
    private Leaderboard leaderboard;

    // EFFECTS:  copies the accList and leaderboard from the parameter, also initializes Json variables
    public SaveLoadManagerUI(AccountList accList, Leaderboard leaderboard) {
        this.accList = accList;
        this.leaderboard = leaderboard;
        initialize();
    }

    // EFFECTS:  initializes Json variables
    private void initialize() {
        jsonWriterAccounts = new JsonWriter(JSON_STORE_ACCOUNTS);
        jsonWriterLeaderboards = new JsonWriter(JSON_STORE_LEADERBOARDS);
        jsonReaderAccounts = new JsonReaderAccounts(JSON_STORE_ACCOUNTS);
        jsonReaderLeaderboards = new JsonReaderLeaderboards(JSON_STORE_LEADERBOARDS);
    }

    public AccountList getAccList() {
        return accList;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    // EFFECTS: saves the accountList to file
    void saveAccountList() {
        try {
            jsonWriterAccounts.open();
            jsonWriterAccounts.write(accList);
            jsonWriterAccounts.close();
            JOptionPane.showMessageDialog(null,"Saved accounts to " + JSON_STORE_ACCOUNTS);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE_ACCOUNTS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accountList from file
    void loadAccountList() {
        try {
            accList = jsonReaderAccounts.read();
            JOptionPane.showMessageDialog(null,"Loaded accounts from " + JSON_STORE_ACCOUNTS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE_ACCOUNTS);
        }
    }

    // EFFECTS: saves the leaderboards to file
    void saveLeaderboards() {
        try {
            jsonWriterLeaderboards.open();
            jsonWriterLeaderboards.write(leaderboard);
            jsonWriterLeaderboards.close();
            JOptionPane.showMessageDialog(null,"Saved leaderboards to " + JSON_STORE_LEADERBOARDS);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE_LEADERBOARDS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accountList from file
    void loadLeaderboards() {
        try {
            leaderboard = jsonReaderLeaderboards.read();
            JOptionPane.showMessageDialog(null,"Loaded leaderboards from " + JSON_STORE_LEADERBOARDS);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE_LEADERBOARDS);
        }
    }

}