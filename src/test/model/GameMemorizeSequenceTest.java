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
        for (char c: game.getSequence()) {
            assertTrue(isArrowCharacter(c));
        }

        game.addOneToSequence();
        game.addOneToSequence();
        assertEquals(3, game.getSequence().size()); // if size is three, adds were successful
        for (char c: game.getSequence()) {
            assertTrue(isArrowCharacter(c));
        }
    }

    @Test
    void testAddOneToSequenceMultiple() {
        int trials = 15;
        for (int i = 0; i < trials; i++) {
            game.addOneToSequence();
        }

        assertEquals(trials, game.getSequence().size());

        for (char c: game.getSequence()) {
            assertTrue(isArrowCharacter(c));
        }
    }

    @Test
    void testWasdToArrow() {
        assertEquals(GameMemorizeSequence.UP_ARROW, game.wasdToArrow('w'));
        assertEquals(GameMemorizeSequence.LEFT_ARROW, game.wasdToArrow('a'));
        assertEquals(GameMemorizeSequence.DOWN_ARROW, game.wasdToArrow('s'));
        assertEquals(GameMemorizeSequence.RIGHT_ARROW, game.wasdToArrow('d'));
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

    // checks if the character key is one of the 4 arrow charaters
    private boolean isArrowCharacter(char c) {
        return (c == GameMemorizeSequence.UP_ARROW
                || c == GameMemorizeSequence.RIGHT_ARROW
                || c == GameMemorizeSequence.DOWN_ARROW
                || c == GameMemorizeSequence.LEFT_ARROW);
    }


}
