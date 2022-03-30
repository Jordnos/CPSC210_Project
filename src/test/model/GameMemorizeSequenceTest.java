package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// unit test class for GameMemorizeSequence
public class GameMemorizeSequenceTest {
    private GameMemorizeSequence game;

    @BeforeEach
    void runBefore() {
        game = new GameMemorizeSequence();
    }

    @Test
    void testAddOneToSequence() {
        assertTrue(game.getSequence().isEmpty());
        game.addOneToSequence();
        assertEquals(1, game.getSequence().size()); // if size is one, add was successful
        for (int i: game.getSequence()) {
            assertTrue(isBetweenValidNumber(i));
        }

        game.addOneToSequence();
        game.addOneToSequence();
        assertEquals(3, game.getSequence().size()); // if size is three, adds were successful
        for (int i: game.getSequence()) {
            assertTrue(isBetweenValidNumber(i));
        }
    }

    @Test
    void testAddOneToSequenceMultiple() {
        int trials = 15;
        for (int i = 0; i < trials; i++) {
            game.addOneToSequence();
        }
        assertEquals(trials, game.getSequence().size());
    }


    @Test
    void testGetScore() {
        assertEquals(0, game.getScore());
        game.addOneToSequence();
        assertEquals(0, game.getScore());
        game.increaseScore();
        assertEquals(1, game.getScore());
        game.increaseScore();
        game.increaseScore();
        assertEquals(3, game.getScore());
    }

    @Test
    void testIsEndedAndSetEnded() {
        assertFalse(game.isEnded());
        game.setEnded(true);
        assertTrue(game.isEnded());
        game.setEnded(true);
        assertTrue(game.isEnded());
        game.setEnded(false);
        assertFalse(game.isEnded());
    }

    private boolean isBetweenValidNumber(int i) {
        return (i >= 0 && i <=8);
    }

}
