package ui;

import model.AccountList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// referenced from "https://www.tutorialsfield.com/login-form-in-java-swing-with-source-code/"
// this class represents a frame where the user can sign up for an account
public class SignupFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton signupButton = new JButton("SIGNUP");
    JCheckBox showPassword = new JCheckBox("Show Password");
    private AccountList accList;

    // EFFECTS:  sets up the frame and copies the accList from the parameter
    public SignupFrame(AccountList list) {
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
        signupButton.setBounds(50, 200, 100, 30);
    }

    // MODIFIES: this
    // EFFECTS:  adds all the fields to the container
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(signupButton);
    }

    // MODIFIES: this
    // EFFECTS:  adds action listeners to the signup button and show password checkbox
    public void addActionEvent() {
        signupButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    // EFFECTS:  checks the action taken adn acts accordingly
    //              if signupButton was clicked, signed up the user for an account if username not taken
    //              if showPassword was checked/unchecked, the password is toggled between shown and not shown
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String userText = userTextField.getText();
            String pwdText = new String(passwordField.getPassword());
            if (accList.addAccount(userText, pwdText)) {
                JOptionPane.showMessageDialog(this, "Signup successful, you are now logged in");
                MenuUI.loggedIn = true;
                MenuUI.accLoggedIn = userText;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Signup failed, username already exists");
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