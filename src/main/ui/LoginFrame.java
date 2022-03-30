package ui;

import model.AccountList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// referenced from "https://www.tutorialsfield.com/login-form-in-java-swing-with-source-code/"
// this class represents a frame where the user can log in to their account
public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JCheckBox showPassword = new JCheckBox("Show Password");
    private AccountList accList;

    // EFFECTS:  sets up the frame and copies the accList from the parameter
    public LoginFrame(AccountList list) {
        this.accList = list;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    // EFFECTS:  sets up the container layout to null
    public void setLayoutManager() {
        container.setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS:  sets up all the fields and their position and sizes
    public void setLocationAndSize() {
        userLabel.setBounds(50, 80, 100, 30);
        passwordLabel.setBounds(50, 130, 100, 30);
        userTextField.setBounds(150, 80, 150, 30);
        passwordField.setBounds(150, 130, 150, 30);
        showPassword.setBounds(150, 160, 150, 30);
        loginButton.setBounds(50, 200, 100, 30);
    }

    // MODIFIES: this
    // EFFECTS:  adds all the fields to the container
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
    }

    // MODIFIES: this
    // EFFECTS:  adds action listeners to the login button and show password checkbox
    public void addActionEvent() {
        loginButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    // EFFECTS:  checks the action taken adn acts accordingly
    //              if loginButton was clicked, logs the user in if the credentials are corrects, otherwise give message
    //              that the user or password is wrong
    //              if showPassword was checked/unchecked, the password is toggled between shown and not shown
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = new String(passwordField.getPassword());
            if (accList.loginAccount(userText, pwdText)) {
                JOptionPane.showMessageDialog(this, "Login successful");
                MenuUI.loggedIn = true;
                MenuUI.accLoggedIn = userText;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }

}