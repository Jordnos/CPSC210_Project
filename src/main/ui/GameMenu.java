package ui;

public class GameMenu extends Menu {
    public static final String COMMAND_GAME_ONE = "1";
    public static final String COMMAND_GAME_TWO = "2";

    protected void processCommand(String command) {
        if (command.equals(COMMAND_GAME_ONE)) {
            runGameOne();
        } else if (command.equals(COMMAND_GAME_TWO)) {
            runGameTwo();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    protected void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> game one");
        System.out.println("\t2 -> game two");
        System.out.println("\tQ -> quit");
    }

    private void runGameOne() {
        //TODO: create game one
    }

    private void runGameTwo() {
        //TODO: create game two
    }
}
