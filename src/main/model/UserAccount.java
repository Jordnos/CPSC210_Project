package model;

import java.util.HashMap;

public class UserAccount {
    private static HashMap<String,UserAccount> accountList = new HashMap<>();
    private String username;
    private String password;

    // EFFECTS:  initializes an account with username and password
    private UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // EFFECTS:  return the password of the account
    protected String getPassword() {
        return password;
    }

    // EFFECTS:  return the password of the account
    protected String getUsername() {
        return username;
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
        return (accountList.containsKey(username)
                && accountList.get(username).getPassword().equals(password));
    }
}
