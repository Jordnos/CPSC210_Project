package model;

// class keeps the password secure
public class UserAccountPassword {
    private String password;

    // EFFECTS:  initializes an account with username and password
    protected UserAccountPassword(String password) {
        this.password = password;
    }

    // EFFECTS:  return the password of the account
    protected String getPassword() {
        return password;
    }

    // MODIFIES: this
    // EFFECTS:  changes the password of the account
    protected void setPassword(String pass) {
        this.password = pass;
    }
}
