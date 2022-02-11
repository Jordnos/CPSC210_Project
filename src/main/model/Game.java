package model;

import java.util.ArrayList;

public abstract class Game {
    private int score = 0;
    private boolean ended = false;

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
