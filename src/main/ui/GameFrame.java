package ui;

import model.GameMemorizeSequence;
import model.Leaderboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame implements ActionListener {

    JButton button0 = new JButton();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    JButton button6 = new JButton();
    JButton button7 = new JButton();
    JButton button8 = new JButton();
    JButton goButton = new JButton("Go");
    JLabel scoreLabel = new JLabel("Score: 0");
    JLabel gameOverPic = new JLabel(new ImageIcon(GAME_OVER_IMAGE));

    private static final Color BUTTON_COLOR = Color.WHITE;
    private static final Color FLASH_COLOR = Color.GREEN;
    private static final String GAME_OVER_IMAGE = "./data/gameOverScreen.jpg";

    private GameMemorizeSequence game;
    private ArrayList<Integer> playerInput = new ArrayList<>();
    private boolean playerTurn = false;
    private ArrayList<JButton> buttons;
    private Leaderboard leaderboard;

    // EFFECTS:  initializes the variables, frame, and starts the game application
    public GameFrame(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;

        setLayout(new BorderLayout());
        initButtons();
        add(makeButtons(), BorderLayout.CENTER);
        add(makeOptionPanel(), BorderLayout.NORTH);
        addListeners();
        setButtonColours();

        start();
    }

    // EFFECTS:  creates a new GameMemorizeSequence
    public int start() {
        game = new GameMemorizeSequence();
        return game.getScore();
    }

    // EFFECTS:  creates the optionPanel for the game and returns it
    private JPanel makeOptionPanel() {
        JPanel optionPanel = new JPanel();
        optionPanel.add(goButton);
        optionPanel.add(scoreLabel);
        return optionPanel;
    }

    // MODIFIES: this
    // EFFECTS:  adds all the buttons into the buttons list
    private void initButtons() {
        buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
    }

    // EFFECTS:  adds all the buttons into a panel and returns it
    private JPanel makeButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3, 10, 10));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS:  sets all the button colours to BUTTON_COLOR
    private void setButtonColours() {
        for (JButton button : buttons) {
            button.setBackground(BUTTON_COLOR);
        }
        goButton.setBackground(FLASH_COLOR);
    }

    // MODIFIES: this
    // EFFECTS:  adds buttonListeners to all the buttons
    private void addListeners() {
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
        goButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS:  starts a round of the game, adds one the answer sequence and sets the playerTurn to true
    private void startRound() throws InterruptedException {
        game.addOneToSequence();
        drawSequence();
        playerTurn = true;
    }

    // MODIFIES: this
    // EFFECTS:  checks if the player input is correct, increases the score or ends the game accordingly
    private void checkPlayerTurn() {
        if (playerInput.size() == game.getSequence().size()) {
            if (!checkPlayerInput()) {
                game.setEnded(true);
                JOptionPane.showMessageDialog(this, gameOverPic, null, JOptionPane.PLAIN_MESSAGE);
                saveToLeaderboard();
                dispose();
            } else {
                game.increaseScore();
                scoreLabel.setText("Score: " + game.getScore());
                playerTurn = false;
                goButton.setBackground(FLASH_COLOR);
            }
        }
    }

    // EFFECTS:  checks if the player input is equal to the answer sequence returns true if so, false otherwise
    private boolean checkPlayerInput() {
        for (int i = 0; i < playerInput.size(); i++) {
            if (!playerInput.get(i).equals(game.getSequence().get(i))) {
                return false;
            }
        }
        playerInput.clear();
        return true;
    }

    // EFFECTS:  draws the answer sequence in the GUI on the buttons for every button
    private void drawSequence() {
        ArrayList<Integer> arr = game.getSequence();
        int count = 0;
        for (int i: arr) {
            flashColor(buttons.get(i), count++ * GameMemorizeSequence.TIME_BETWEEN_SEQUENCE * 2);
        }
    }

    // referenced "https://coderanch.com/t/335559/java/flash-JButton-background"
    // EFFECTS:  makes a timer and flashes the button once
    private void flashColor(JButton button, int initialDelay) {
        Timer flashSet = new Timer(GameMemorizeSequence.TIME_BETWEEN_SEQUENCE, new FlashListener(button));
        flashSet.setInitialDelay(initialDelay);
        flashSet.start();
    }

    // referenced "https://coderanch.com/t/335559/java/flash-JButton-background"
    // EFFECTS:  makes a timer and flashes the button once
    private class FlashListener implements ActionListener {
        JButton button;

        public FlashListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.getBackground().equals(BUTTON_COLOR)) {
                button.setBackground(FLASH_COLOR);
            } else {
                button.setBackground(BUTTON_COLOR);
                ((Timer)e.getSource()).stop();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  saves the current score to the leaderboard
    private void saveToLeaderboard() {
        if (MenuUI.loggedIn) {
            leaderboard.addToLeaderboard(game.getScore(), MenuUI.accLoggedIn);
        }
    }

    // MODIFIES: this
    // EFFECTS:  checks if a button is pressed and acts accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        if (playerTurn) {
            for (int i = 0; i < buttons.size(); i++) {
                if (e.getSource() == buttons.get(i)) {
                    playerInput.add(i);
                    checkPlayerTurn();
                }
            }
        } else if (e.getSource() == goButton && !playerTurn) {
            try {
                goButton.setBackground(BUTTON_COLOR);
                startRound();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
