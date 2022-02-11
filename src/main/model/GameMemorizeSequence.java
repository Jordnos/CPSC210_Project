package model;

import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.Random;

public class GameMemorizeSequence extends Game {
    public static final int TIME_PER_SEQUENCE = 1000;
    public static final int TIME_BETWEEN_SEQUENCE = 1000;
    public static final char UP_ARROW = '^';
    public static final char DOWN_ARROW = 'v';
    public static final char LEFT_ARROW = 60;
    public static final char RIGHT_ARROW = 62;

    private ArrayList<Character> sequence;
    private final int maxX;
    private final int maxY;

    public GameMemorizeSequence(int maxX, int maxY) {
        sequence = new ArrayList<>();
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public ArrayList<Character> getSequence() {
        return sequence;
    }

    // EFFECTS:  generates random number between 0 and 3 (inclusive)
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

    @Override
    public int getScore() {
        return sequence.size();
    }
}
