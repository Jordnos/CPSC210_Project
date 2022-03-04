package persistence;

import model.AccountList;
import model.Leaderboard;
import model.UserAccount;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonReaderLeaderboardTest {

    @Test
    void testReaderNonExistentFileLeaderboard() {
        JsonReaderLeaderboards reader = new JsonReaderLeaderboards("./data/noSuchFile.json");
        try {
            Leaderboard list = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLeaderboard() {
        JsonReaderLeaderboards reader = new JsonReaderLeaderboards("./data/testReaderEmptyLeaderboard.json");
        try {
            Leaderboard list = reader.read();
            assertTrue(list.getScoresList().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLeaderboard() {
        JsonReaderLeaderboards reader = new JsonReaderLeaderboards("./data/testReaderGeneralLeaderboard.json");
        try {
            Leaderboard list = reader.read();
            Map<String, Integer> testList = list.getScoresList();
            assertEquals(3, testList.size());
            assertEquals(1, testList.get("user1"));
            assertEquals(2, testList.get("user2"));
            assertEquals(3, testList.get("user3"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
