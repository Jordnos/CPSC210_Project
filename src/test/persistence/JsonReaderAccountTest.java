package persistence;

import model.AccountList;
import model.UserAccount;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

// referenced from JsonSerializationDemo
// unit test for JsonReader
public class JsonReaderAccountTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderAccounts reader = new JsonReaderAccounts("./data/noSuchFile.json");
        try {
            AccountList list = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccountList() {
        JsonReaderAccounts reader = new JsonReaderAccounts("./data/testReaderEmptyAccountList.json");
        try {
            AccountList list = reader.read();
            assertTrue(list.getList().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccountList() {
        JsonReaderAccounts reader = new JsonReaderAccounts("./data/testReaderGeneralAccountList.json");
        try {
            AccountList list = reader.read();
            Map<String, UserAccount> testList = list.getList();
            assertEquals(3, testList.size());
            checkAccount("user1", "pass1", testList.get("user1"));
            checkAccount("user2", "pass2", testList.get("user2"));
            checkAccount("user3", "pass3", testList.get("user3"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    void checkAccount(String username, String password, UserAccount acc) {
        assertEquals(username, acc.getUsername());
        assertEquals(password, acc.getPassword());
    }

}
