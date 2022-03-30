package model;

import org.json.JSONObject;
import persistence.Writable;

// class with a username and password
public class UserAccount implements Writable {
    private String username;
    private String password;

    // EFFECTS:  initializes the password
    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // EFFECTS:  return the password
    public String getPassword() {
        return password;
    }

    // EFFECTS:  return the username
    public String getUsername() {
        return username;
    }

    // MODIFIES: this
    // EFFECTS:  changes the password
    protected void setPassword(String pass) {
        this.password = pass;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);
        return json;
    }

}
