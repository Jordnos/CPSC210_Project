package model;

public class UserAccount {
    private String username;
    private String password;

    //EFFECTS:  initializes an account with username and password
    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected String getPassword() {
        return password;
    }
}
