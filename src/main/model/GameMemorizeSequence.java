package model;

import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.Random;

public class GameMemorizeSequence {
    public static final int TIME_PER_CHAR_IN_SEQUENCE = 1000;
    public static final int TIME_BETWEEN_SEQUENCE = 1000;
    public static final char UP_ARROW = '^';
    public static final char DOWN_ARROW = 'v';
    public static final char LEFT_ARROW = 60;
    public static final char RIGHT_ARROW = 62;

    private ArrayList<Character> sequence;
    private int score = 0;
    private boolean ended = false;

    // EFFECTS:  initializes sequence, maxX and maxY
    public GameMemorizeSequence() {
        sequence = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:  generates one of the 4 arrow characters and adds it to the sequence
    public void addOneToSequence() {
        Random rand = new Random();
        int i = rand.nextInt(4);
        char c;
        switch (i) {
            case 0:
                c = UP_ARROW;
                break;
            case 1:
                c = RIGHT_ARROW;
                break;
            case 2:
                c = DOWN_ARROW;
                break;
            default:
                c = LEFT_ARROW;
        }
        sequence.add(c);
    }

    // REQUIRES: c is either w, a, s or d
    // EFFECTS:  returns the character correlated to the code integer
    public char wasdToArrow(char c) {
        switch (c) {
            case 'w':
                return UP_ARROW;
            case 'd':
                return RIGHT_ARROW;
            case 's':
                return DOWN_ARROW;
            default:
                return LEFT_ARROW;
        }
    }

    // MODIFIES: this
    // EFFECTS:  increases score by one
    public void increaseScore() {
        score++;
    }

    public ArrayList<Character> getSequence() {
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
