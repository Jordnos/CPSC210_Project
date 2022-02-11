package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// unit test class for AccountList, also tests UserAccount while testing
class AccountListTest {
    private AccountList list;
    private String user1;
    private String user2;
    private String pass1;
    private String pass2;

    @BeforeEach
    void runBefore() {
        list = new AccountList();
        user1 = "user1";
        user2 = "user2";
        pass1 = "pass1";
        pass2 = "pass2";
        list.addAccount(user1, pass1);
    }

    @Test
    void testAddAccount() {
        assertFalse(list.addAccount(user1, pass1));      // repeat
        assertTrue(list.addAccount(user2, pass1));       // new acc
        assertFalse(list.addAccount(user2, pass2));      // repeat with diff pass

        String userEmpty = "";
        String passEmpty = "";
        assertTrue(list.addAccount(userEmpty, passEmpty));

    }

    @Test
    void testLoginAccountFail() {
        assertFalse(list.loginAccount(user2, pass2));    // no acc made
        assertFalse(list.loginAccount(user2, pass1));    // no acc made
        assertFalse(list.loginAccount(user1, pass2));    // wrong pass

    }

    @Test
    void testLoginAccountSuccess() {
        list.addAccount(user2, pass2);
        assertTrue(list.loginAccount(user1, pass1));
        assertTrue(list.loginAccount(user1, pass1));     // repeat login
        assertTrue(list.loginAccount(user2, pass2));
    }

    @Test
    void testUsernameTaken() {
        assertTrue(list.usernameTaken(user1));
        assertFalse(list.usernameTaken(user2));
        list.addAccount(user2, pass2);
        assertTrue(list.usernameTaken(user2));
    }

    @Test
    void testChangePassFail() {
        assertFalse(list.changePass(user2,pass1));
        assertFalse(list.changePass(user2,pass2));
    }

    @Test
    void testChangePassSuccess() {
        assertTrue(list.loginAccount(user1, pass1));
        assertTrue(list.changePass(user1,pass2));
        assertTrue(list.loginAccount(user1, pass2));
        assertTrue(list.changePass(user1,pass1));
        assertTrue(list.loginAccount(user1, pass1));
    }

}