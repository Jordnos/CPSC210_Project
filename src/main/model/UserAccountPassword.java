package model;

// class keeps the password protected from outside classes accessing it
public class UserAccountPassword {
    private String password;

    // EFFECTS:  initializes the password
    protected UserAccountPassword(String password) {
        this.password = password;
    }

    // EFFECTS:  return the password
    protected String getPassword() {
        return password;
    }

    // MODIFIES: this
    // EFFECTS:  changes the password
    protected void setPassword(String pass) {
        this.password = pass;
    }
}
