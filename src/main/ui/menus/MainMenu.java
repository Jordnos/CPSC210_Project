package ui.menus;

import model.AccountList;
import model.Leaderboard;
import persistence.JsonReaderAccounts;
import persistence.JsonReaderLeaderboards;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

// a menu so you can select other menus
public class MainMenu extends Menu {
    public static final String COMMAND_GAME = "G";
    public static final String COMMAND_LEADERBOARD = "L";
    public static final String COMMAND_ACCOUNT = "A";
    public static final String COMMAND_SAVE = "S";
    public static final String COMMAND_LOAD = "O";

    public static final String JSON_STORE_ACCOUNTS = "./data/accountList.json";
    public static final String JSON_STORE_LEADERBOARDS = "./data/leaderboards.json";

    private JsonWriter jsonWriterAccounts;
    private JsonWriter jsonWriterLeaderboards;
    private JsonReaderAccounts jsonReaderAccounts;
    private JsonReaderLeaderboards jsonReaderLeaderboards;

    // EFFECTS:  starts the runMenu
    public MainMenu() throws IOException, InterruptedException {
        super();
    }

    // MODIFIES: this
    // EFFECTS:  initializes variables
    @Override
    protected void initialize() {
        super.initialize();
        jsonWriterAccounts = new JsonWriter(JSON_STORE_ACCOUNTS);
        jsonWriterLeaderboards = new JsonWriter(JSON_STORE_LEADERBOARDS);
        jsonReaderAccounts = new JsonReaderAccounts(JSON_STORE_ACCOUNTS);
        jsonReaderLeaderboards = new JsonReaderLeaderboards(JSON_STORE_LEADERBOARDS);
        loggedIn = false;
        accLoggedIn = null;
        list = new AccountList();
        leaderboardMemorizeSequence = new Leaderboard(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE);
    }

    // EFFECTS:  runs the command and calls the correlating method
    protected void processCommand(String command) throws IOException, InterruptedException {
        switch (command) {
            case COMMAND_GAME:
                gameMenu();
                break;
            case COMMAND_LEADERBOARD:
                leaderboardMenu();
                break;
            case COMMAND_ACCOUNT:
                accountMenu();
                break;
            case COMMAND_SAVE:
                saveAccountList();
                saveLeaderboards();
                break;
            case COMMAND_LOAD:
                loadAccountList();
                loadLeaderboards();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    // EFFECTS:  displays the menu
    protected void displayMenu() {
        super.displayMenu();
        System.out.println("Select from:");
        System.out.println("\tG -> game selection");
        System.out.println("\tL -> leaderboards");
        System.out.println("\tA -> account manager");
        System.out.println("\tS -> save(overwrites prev save) to file");
        System.out.println("\tO -> load from file");
        System.out.println("\tQ -> quit");
    }

    // EFFECTS:  runs the game menu
    private void gameMenu() throws IOException, InterruptedException {
        new GameMenu();
    }

    // EFFECTS:  runs the leaderboard menu
    private void leaderboardMenu() throws IOException, InterruptedException {
        new LeaderboardMenu();
    }

    // EFFECTS:  runs the account menu
    private void accountMenu() throws IOException, InterruptedException {
        new AccountMenu();
    }

    // EFFECTS: saves the accountList to file
    private void saveAccountList() {
        try {
            jsonWriterAccounts.open();
            jsonWriterAccounts.write(list);
            jsonWriterAccounts.close();
            System.out.println("Saved accounts to " + JSON_STORE_ACCOUNTS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ACCOUNTS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accountList from file
    private void loadAccountList() {
        try {
            list = jsonReaderAccounts.read();
            System.out.println("Loaded accounts from " + JSON_STORE_ACCOUNTS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ACCOUNTS);
        }
    }

    // EFFECTS: saves the leaderboards to file
    private void saveLeaderboards() {
        try {
            jsonWriterLeaderboards.open();
            jsonWriterLeaderboards.write(leaderboardMemorizeSequence);
            jsonWriterLeaderboards.close();
            System.out.println("Saved leaderboards to " + JSON_STORE_LEADERBOARDS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_LEADERBOARDS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accountList from file
    private void loadLeaderboards() {
        try {
            leaderboardMemorizeSequence = jsonReaderLeaderboards.read();
            System.out.println("Loaded leaderboards from " + JSON_STORE_LEADERBOARDS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_LEADERBOARDS);
        }
    }

}
