package ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.GameMemorizeSequence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameTerminal {
    private GameMemorizeSequence game; // testing on one game
    private Screen screen;
    private WindowBasedTextGUI endGui;
    private Scanner input;

    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        input = new Scanner(System.in);

        TerminalSize terminalSize = screen.getTerminalSize();

        game = new GameMemorizeSequence((terminalSize.getColumns() - 1),terminalSize.getRows() - 1);

        startGame();
    }

    private void startGame() throws IOException, InterruptedException {
        while (!game.isEnded() || endGui.getActiveWindow() != null) {
            screen.clear();
            render();
            screen.refresh();

            System.out.println("Type anything to start round. ");
            input.next();
            startRound();
            //Thread.sleep(100);
        }

        System.exit(0);
    }

    private void render() {
        if (game.isEnded()) {
            if (endGui == null) {
                drawEndScreen();
            }
            return;
        }
        drawScore();
    }

    private void startRound() throws InterruptedException, IOException {
        game.addOneToSequence();
        drawSequence();
        if (!playerInput()) {
            game.setEnded(true);
            drawEndScreen();
        }
    }

    private void drawSequence() throws InterruptedException, IOException {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        ArrayList<Character> arr = game.getSequence();
        for (char c: arr) {
            render();
            String temp = "" + c;
            text.putString(1, 1, temp);
            screen.refresh();
            Thread.sleep(GameMemorizeSequence.TIME_PER_SEQUENCE);
            screen.clear();
            render();
            screen.refresh();
            Thread.sleep(GameMemorizeSequence.TIME_BETWEEN_SEQUENCE);
        }
    }

    private boolean playerInput() {
        ArrayList<Character> arr = new ArrayList<>();
        System.out.println("Enter the sequence: ");
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

    private void drawEndScreen() {
        endGui = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("Game over!")
                .setText("You finished with a score of " + game.getScore() + "!")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(endGui);
    }

    private void drawScore() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.GREEN);
        text.putString(1, 0, "Score: ");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(8, 0, String.valueOf(game.getScore()));
    }

}
