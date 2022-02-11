package ui;

import java.io.IOException;

public class GameMenu extends Menu {
    public static final String COMMAND_GAME_ONE = "1";
    public static final String COMMAND_GAME_TWO = "2";

    public GameMenu() throws IOException, InterruptedException {
        super();
    }

    // EFFECTS:  runs the command and calls the correlating method
    protected void processCommand(String command) throws IOException, InterruptedException {
        switch (command) {
            case COMMAND_GAME_ONE:
                runGameOne();
                break;
            case COMMAND_GAME_TWO:
                runGameTwo();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    // EFFECTS:  displays the menu
    protected void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> game one");
        System.out.println("\t2 -> game two");
        System.out.println("\tQ -> back");
    }

    // EFFECTS:  runs game 1
    private void runGameOne() throws IOException, InterruptedException {
        GameTerminal gameHandler = new GameTerminal();
        gameHandler.start();
    }

    // EFFECTS:  runs game 2
    private void runGameTwo() {
        //TODO: create game two
    }
}
