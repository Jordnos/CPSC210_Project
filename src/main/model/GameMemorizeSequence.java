package model;

import java.util.ArrayList;
import java.util.Random;

// class holds all the information and methods needed to run the game Memorize Sequence
public class GameMemorizeSequence {
    public static final int TIME_BETWEEN_SEQUENCE = 500;

    private ArrayList<Integer> sequence;
    private int score = 0;
    private boolean ended = false;

    // EFFECTS:  initializes the sequence as an array
    public GameMemorizeSequence() {
        sequence = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:  generates one of the 4 arrow characters and adds it to the sequence
    public void addOneToSequence() {
        Random rand = new Random();
        int i = rand.nextInt(9);
        sequence.add(i);
    }

    // MODIFIES: this
    // EFFECTS:  increments score by one
    public void increaseScore() {
        score++;
    }

    public ArrayList<Integer> getSequence() {
        return sequence;
    }

    public int getScore() {
        return score;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
