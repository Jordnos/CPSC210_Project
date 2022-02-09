package model;

import java.util.HashMap;

public class AccountList {
    private static HashMap<String,UserAccount> accountList;

    // EFFECTS:  initializes a new account list
    public AccountList() {
    }

    // MODIFIES: this
    // EFFECTS:  creates an account and adds it to the accountlist if account with username doesn't already exist
    //           otherwise return false
    public static boolean addAccount(String username, String password) {
        UserAccount acc = new UserAccount(username, password);
        if (accountList.containsKey(username)) {
            return false;
        }
        accountList.put(username,acc);
        return true;
    }

    // EFFECTS:  return true if username and password match oan account on the accountlist
    public static boolean loginAccount(String username, String password) {
        if (accountList.containsKey(username) && accountList.get(username).getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
