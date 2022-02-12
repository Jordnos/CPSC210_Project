package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// unit test class for Leaderboard
public class LeaderboardTest {
    private Leaderboard list;
    private String user1;

    @BeforeEach
    void runBefore() {
        list = new Leaderboard(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE);
        user1 = "user1";
    }

    @Test
    void testAddToLeaderboardZero() {
        assertFalse(list.getScoresList().containsKey(0));
        list.addToLeaderboard(0, user1);
        assertTrue(list.getScoresList().containsKey(0));
        assertEquals(user1, list.getScoresList().get(0).get(0));
    }

    @Test
    void testAddToLeaderboardNegative() {
        assertFalse(list.getScoresList().containsKey(-1));
        list.addToLeaderboard(-1, user1);
        assertTrue(list.getScoresList().containsKey(-1));
        assertEquals(user1, list.getScoresList().get(-1).get(0));
    }

    @Test
    void testAddToLeaderboardRepeats() {
        String user2 = "user2";
        assertFalse(list.getScoresList().containsKey(5));
        list.addToLeaderboard(5, user1);
        assertTrue(list.getScoresList().containsKey(5));

        assertEquals(user1, list.getScoresList().get(5).get(0));
        assertEquals(1,list.getScoresList().get(5).size());

        list.addToLeaderboard(5, user2);
        assertEquals(user2, list.getScoresList().get(5).get(1));
        assertEquals(2,list.getScoresList().get(5).size());

        list.addToLeaderboard(5, user2);
        assertTrue(list.getScoresList().containsKey(5));
        assertEquals(2,list.getScoresList().get(5).size());
    }
}
