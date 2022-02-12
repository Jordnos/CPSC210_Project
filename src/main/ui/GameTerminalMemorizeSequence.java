package ui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.GameMemorizeSequence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// SnakeConsole code is used as reference
// runs the Memorize Sequence game
public class GameTerminalMemorizeSequence {
    private GameMemorizeSequence game;
    private Screen screen;
    private WindowBasedTextGUI endGui;
    private Scanner input;

    // MODIFIES: this
    // EFFECTS:  starts the game, returns the score of the game after
    public int start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        input = new Scanner(System.in);

        game = new GameMemorizeSequence();

        startGame();
        return game.getScore();
    }

    // MODIFIES: this
    // EFFECTS:  begins game cycle, and keeps starting rounds until game is ended and endGui is exited
    private void startGame() throws IOException, InterruptedException {
        while (!game.isEnded() || endGui.getActiveWindow() != null) {
            screen.clear();
            render();
            screen.refresh();

            System.out.println("Type anything to start round. ");
            input.next();
            startRound();
        }
        screen.close();
    }

    // MODIFIES: this
    // EFFECTS:  renders the screen, draws the end screen if game is ended, otherwise
    //           draws the score
    private void render() {
        if (game.isEnded()) {
            if (endGui == null) {
                drawEndScreen();
            }
            return;
        }
        drawScore();
    }

    // MODIFIES: this
    // EFFECTS:  starts a round of the game
    //           if the player input was wrong, ends the game and draws the endscreen
    private void startRound() throws InterruptedException, IOException {
        game.addOneToSequence();
        drawSequence();
        if (!playerInput()) {
            game.setEnded(true);
            drawEndScreen();
        } else {
            game.increaseScore();
        }
    }

    // MODIFIES: this
    // EFFECTS:  draws the sequence in a blinking effect
    private void drawSequence() throws InterruptedException, IOException {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        ArrayList<Character> arr = game.getSequence();
        for (char c: arr) {
            render();
            String temp = "" + c;
            text.putString(1, 1, temp);
            screen.refresh();
            Thread.sleep(GameMemorizeSequence.TIME_PER_CHAR_IN_SEQUENCE);
            screen.clear();
            render();
            screen.refresh();
            Thread.sleep(GameMemorizeSequence.TIME_BETWEEN_SEQUENCE);
        }
    }

    // MODIFIES: this
    // EFFECTS:  prompts the user to input the sequence
    //           if the final input is right returns true, otherwise returns false
    private boolean playerInput() {
        ArrayList<Character> arr = new ArrayList<>();
        System.out.println("Use w a s d for directions. ");
        System.out.println("Enter the sequence (one key at a time): ");
        for (int i = 0; i < game.getSequence().size(); i++) {
            String s = input.next();
            char c = game.wasdToArrow(s.charAt(0));
            arr.add(c);
        }
        for (int i = 0; i < arr.size(); i++) {
            if (!(game.getSequence().get(i) == arr.get(i))) {
                return false;
            }
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS:  draws the end screen
    private void drawEndScreen() {
        endGui = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("Game over!")
                .setText("You finished with a score of " + game.getScore() + "!")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(endGui);
    }

    // MODIFIES: this
    // EFFECTS:  draws the score
    private void drawScore() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.GREEN);
        text.putString(1, 0, "Score: ");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(8, 0, String.valueOf(game.getScore()));
    }

}
