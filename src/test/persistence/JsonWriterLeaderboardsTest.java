package persistence;

import model.Leaderboard;
import model.UserAccount;
import model.AccountList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

// referenced from JsonSerializationDemo
// unit test for JsonWriterLeaderboard
public class JsonWriterLeaderboardsTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Leaderboard list = new Leaderboard(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            writer.write(list);
            writer.close();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLeaderboard() {
        try {
            Leaderboard list = new Leaderboard(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeaderboard.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReaderLeaderboards reader = new JsonReaderLeaderboards("./data/testWriterEmptyLeaderboard.json");
            list = reader.read();
            Map<String,Integer> testList = list.getScoresList();
            assertTrue(testList.isEmpty());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLeaderboard() {
        try {
            Leaderboard list = new Leaderboard(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE);
            list.addToLeaderboard(1, "user1");
            list.addToLeaderboard(2, "user2");
            list.addToLeaderboard(3, "user3");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLeaderboard.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReaderLeaderboards reader = new JsonReaderLeaderboards("./data/testWriterGeneralLeaderboard.json");
            list = reader.read();
            Map<String, Integer> testList = list.getScoresList();
            assertEquals(1,testList.get("user1"));
            assertEquals(2,testList.get("user2"));
            assertEquals(3,testList.get("user3"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
