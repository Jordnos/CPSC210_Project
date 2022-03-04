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
        assertFalse(list.getScoresList().containsKey(user1));
        list.addToLeaderboard(0, user1);
        assertTrue(list.getScoresList().containsKey(user1));
        assertEquals(0, list.getScoresList().get(user1));
    }

    @Test
    void testAddToLeaderboardNegative() {
        assertFalse(list.getScoresList().containsKey(user1));
        list.addToLeaderboard(-1, user1);
        assertTrue(list.getScoresList().containsKey(user1));
        assertEquals(-1, list.getScoresList().get(user1));
    }

    @Test
    void testAddToLeaderboardRepeats() {
        String user2 = "user2";
        list.addToLeaderboard(5, user1);
        assertEquals(5, list.getScoresList().get(user1));
        list.addToLeaderboard(5, user1);
        assertEquals(5, list.getScoresList().get(user1));
        list.addToLeaderboard(5, user2);
        assertEquals(5, list.getScoresList().get(user2));
        list.addToLeaderboard(5, user2);
        assertEquals(5, list.getScoresList().get(user2));
        list.addToLeaderboard(5, user2);
        assertEquals(5, list.getScoresList().get(user2));
    }

    @Test
    void testIncreasingScoresAddToLeaderboard() {
        list.addToLeaderboard(1, user1);
        assertEquals(1, list.getScoresList().get(user1));
        list.addToLeaderboard(2, user1);
        assertEquals(2, list.getScoresList().get(user1));
        list.addToLeaderboard(5, user1);
        assertEquals(5, list.getScoresList().get(user1));
        list.addToLeaderboard(3, user1);
        assertEquals(5, list.getScoresList().get(user1));
        list.addToLeaderboard(5, user1);
        assertEquals(5, list.getScoresList().get(user1));
    }

    @Test
    void testGetGameTitle() {
        assertEquals(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE, list.getGameTitle());
    }
}
