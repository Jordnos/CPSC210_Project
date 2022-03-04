package persistence;

import model.UserAccount;
import model.AccountList;
import model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

// referenced from JsonSerializationDemo
// unit test for JsonWriter
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            AccountList list = new AccountList();
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
    void testWriterEmptyAccountList() {
        try {
            AccountList list = new AccountList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccountList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccountList.json");
            list = reader.read();
            Map<String,UserAccount> testList = list.getList();
            assertTrue(testList.isEmpty());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            AccountList list = new AccountList();
            list.addAccount("user1","pass1");
            list.addAccount("user2","pass2");
            list.addAccount("user3","pass3");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccountList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccountList.json");
            list = reader.read();
            Map<String, UserAccount> testList = list.getList();
            int i = 1;
            for(Map.Entry<String,UserAccount> entry : testList.entrySet()) {
                assertEquals("user" + i,entry.getKey());
                assertEquals("user" + i,entry.getValue().getUsername());
                assertEquals("pass" + i,entry.getValue().getPassword());
                i++;
            }

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
