package ui;

import model.AccountList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// referenced from "https://www.tutorialsfield.com/login-form-in-java-swing-with-source-code/"
// this class represents a frame where the user can log in to their account
public class PasswordChangeFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel oldPasswordLabel = new JLabel("OLD PASSWORD");
    JLabel newPasswordLabel = new JLabel("NEW PASSWORD");
    JPasswordField oldPasswordField = new JPasswordField();
    JPasswordField newPasswordField = new JPasswordField();
    JButton confirmButton = new JButton("CONFIRM");
    JCheckBox showPasswords = new JCheckBox("Show Passwords");
    private AccountList accList;

    // EFFECTS:  sets up the frame and copies the accList from the parameter
    public PasswordChangeFrame(AccountList list) {
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
        oldPasswordLabel.setBounds(50, 80, 100, 30);
        newPasswordLabel.setBounds(50, 130, 100, 30);
        oldPasswordField.setBounds(150, 80, 150, 30);
        newPasswordField.setBounds(150, 130, 150, 30);
        showPasswords.setBounds(150, 160, 150, 30);
        confirmButton.setBounds(50, 200, 100, 30);
    }

    // MODIFIES: this
    // EFFECTS:  adds all the fields to the container
    public void addComponentsToContainer() {
        container.add(oldPasswordLabel);
        container.add(newPasswordLabel);
        container.add(oldPasswordField);
        container.add(newPasswordField);
        container.add(showPasswords);
        container.add(confirmButton);
    }

    // MODIFIES: this
    // EFFECTS:  adds action listeners to the login button and show password checkbox
    public void addActionEvent() {
        confirmButton.addActionListener(this);
        showPasswords.addActionListener(this);
    }


    // EFFECTS:  checks the action taken and acts accordingly
    //              if changePasswordButton was clicked, changes the old password to the new one, if the old
    //              password inputted is correct
    //              if showPassword was checked/unchecked, the password is toggled between shown and not shown
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            String oldPass;
            String newPass;
            oldPass = new String(oldPasswordField.getPassword());
            newPass = new String(newPasswordField.getPassword());
            if (accList.getList().get(MenuUI.accLoggedIn).getPassword().equals(oldPass)) {
                accList.changePass(MenuUI.accLoggedIn, newPass);
                JOptionPane.showMessageDialog(this, "Password Change successful");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Old Password");
            }
        }

        if (e.getSource() == showPasswords) {
            if (showPasswords.isSelected()) {
                oldPasswordField.setEchoChar((char) 0);
                newPasswordField.setEchoChar((char) 0);
            } else {
                oldPasswordField.setEchoChar('*');
                newPasswordField.setEchoChar('*');
            }
        }
    }

}