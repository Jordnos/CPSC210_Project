package model;

import java.util.HashMap;

public class AccountList {
    private HashMap<String, UserAccountPassword> list;

    public AccountList() {
        list = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS:  creates an account and adds it to the accountlist if account with username doesn't already exist
    //           otherwise return false
    public boolean addAccount(String username, String password) {
        UserAccountPassword acc = new UserAccountPassword(password);
        if (usernameTaken(username)) {
            return false;
        }
        list.put(username,acc);
        return true;
    }

    // EFFECTS:  return true if username and password match an account on the accountlist
    public boolean loginAccount(String username, String password) {
        return (list.containsKey(username)
                && list.get(username).getPassword().equals(password));
    }

    // EFFECTS:  return true if username is taken, false otherwise
    public boolean usernameTaken(String username) {
        return list.containsKey(username);
    }

    // MODIFIES: this
    // EFFECTS:  changes password to new password, if successful return true,
    //           otherwise return false
    public boolean changePass(String username, String password) {
        if (list.containsKey(username)) {
            list.get(username).setPassword(password);
            return true;
        }
        return false;
    }
}


