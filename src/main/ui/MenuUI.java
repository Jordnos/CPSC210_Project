package ui;

import model.AccountList;
import model.Leaderboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// referenced from AlarmSystem
// This class represents the menu window frame where the user can go through the application
public class MenuUI extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    static boolean loggedIn;
    static String accLoggedIn;
    AccountList accList;
    Leaderboard leaderboard;

    // EFFECTS:  creates the main frame and adds a tabbed pane with menus to it
    public MenuUI() {
        super();
        setSize(WIDTH, HEIGHT);

        initialize();
        addMenus();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  initializes all the private variables of the frame
    private void initialize() {
        loggedIn = false;
        accLoggedIn = null;
        accList = new AccountList();
        leaderboard = new Leaderboard(Leaderboard.LEADERBOARD_GAME_MEMORIZE_SEQUENCE);
        updateTitle();
    }

    // EFFECTS:  initializes all the different tabs and adds it to the main frame
    private void addMenus() {
        JTabbedPane menus = new JTabbedPane();
        menus.addTab("Game", makeGame());
        menus.addTab("Leaderboard", makeLeaderboard());
        menus.addTab("Account Manager", makeAccountManager());
        menus.addTab("Save/Load", makeSaveLoadButtons());
        this.add(menus);
    }

    // EFFECTS:  returns a panel with a specified layout which all the tabs will have
    private JPanel newTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        return panel;
    }

    // EFFECTS:  makes the game panel that has buttons to start the game and returns it
    private JComponent makeGame() {
        JPanel panel = newTab();
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new StartGameButtonListener());
        panel.add(startGameButton);
        return panel;
    }

    // EFFECTS:  makes the leaderboard tab that has leaderboard related options returns it
    private JComponent makeLeaderboard() {
        JPanel panel = newTab();
        JButton updateButton = new JButton("Show Leaderboard");
        updateButton.addActionListener(new UpdateLeaderboardListener());
        panel.add(updateButton);
        return panel;
    }

    // EFFECTS:  makes the account manager tab that has account related options returns it
    private JComponent makeAccountManager() {
        JPanel panel = newTab();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton loginButton = new JButton("Login");
        JButton logoutButton = new JButton("Logout");
        JButton signupButton = new JButton("Signup");
        JButton accountListButton = new JButton("Active Accounts");

        loginButton.addActionListener(new LoginButtonListener());
        logoutButton.addActionListener(new LogoutButtonListener());
        signupButton.addActionListener(new SignupButtonListener());
        accountListButton.addActionListener(new AccountListButtonListener());

        panel.add(loginButton);
        panel.add(logoutButton);
        panel.add(signupButton);
        panel.add(accountListButton);

        return panel;
    }

    // EFFECTS:  makes the save/load tab that has save/load related options returns it
    private JComponent makeSaveLoadButtons() {
        JPanel panel = newTab();
        JButton saveButton = new JButton("Save State");
        JButton loadButton = new JButton("Load State");

        saveButton.addActionListener(new SaveStateButtonListener());
        loadButton.addActionListener(new LoadStateButtonListener());

        panel.add(saveButton);
        panel.add(loadButton);
        return panel;
    }

    // EFFECTS:  updates the title of the frame to match if a user is logged in
    private void updateTitle() {
        if (loggedIn) {
            setTitle("Application, User Logged: " + accLoggedIn);
        } else {
            setTitle("Application, No User Logged");
        }
    }

    // starts the application
    public static void main(String[] args) {
        new MenuUI();
    }

    // listener class for the button to start game
    private class StartGameButtonListener implements ActionListener {

        //  EFFECTS:  creates a GameFrame where the game starts
        @Override
        public void actionPerformed(ActionEvent e) {
            GameFrame frame = new GameFrame(leaderboard);
            frame.setTitle("Game");
            frame.setVisible(true);
            frame.setBounds(100,200,600,650);
            frame.setResizable(false);
        }
    }

    // listener class for the button to open the leaderboard
    private class UpdateLeaderboardListener implements ActionListener {

        //  EFFECTS:  creates a LeaderboardFrame where the game leaderboard is shown
        @Override
        public void actionPerformed(ActionEvent e) {
            LeaderboardFrame frame = new LeaderboardFrame(leaderboard);
            frame.setTitle("Leaderboard");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
            frame.setResizable(false);
        }
    }

    // listener class for the button to save the game state
    private class SaveStateButtonListener implements ActionListener {

        //  EFFECTS:  saves the application state, accounts and leaderboards
        @Override
        public void actionPerformed(ActionEvent e) {
            SaveLoadManagerUI saveLoadManager = new SaveLoadManagerUI(accList, leaderboard);
            saveLoadManager.saveAccountList();
            saveLoadManager.saveLeaderboards();
        }
    }

    // listener class for the button to load the game state
    private class LoadStateButtonListener implements ActionListener {

        // MODIFIES: this
        //  EFFECTS:  loads the application state, accounts and leaderboards
        @Override
        public void actionPerformed(ActionEvent e) {
            SaveLoadManagerUI saveLoadManager = new SaveLoadManagerUI(accList, leaderboard);
            saveLoadManager.loadAccountList();
            saveLoadManager.loadLeaderboards();
            accList = saveLoadManager.getAccList();
            leaderboard = saveLoadManager.getLeaderboard();
        }
    }

    // listener class for the button to login to the application
    private class LoginButtonListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS:  if a user is not logged in, opens a LoginFrame to give a chance for
        //              the user to login, updates the title accordingly
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loggedIn) {
                JOptionPane.showMessageDialog(null,"Already Logged In");
            } else {
                LoginFrame frame = new LoginFrame(accList);
                frame.setTitle("Login Form");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 360);
                frame.setResizable(false);
                frame.addWindowListener(new UpdateTitleAdapter());
            }
        }
    }

    // listener class for the button to logout of the application
    private class LogoutButtonListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS:  if a user is logged in, logs them out and updates the title
        //              otherwise does nothing
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!loggedIn) {
                JOptionPane.showMessageDialog(null,"You are not logged in");
            } else {
                loggedIn = false;
                accLoggedIn = null;
                JOptionPane.showMessageDialog(null,"Logout successful");
            }
            updateTitle();
        }
    }

    // listener class for the button to signup for the application
    private class SignupButtonListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS:  if a user is not logged in, give the user a chance to sign up for an account,
        //              automatically logs into the account if successful, updates the title accordingly
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loggedIn) {
                JOptionPane.showMessageDialog(null,"Already logged on, please log out first");
            } else {
                SignupFrame frame = new SignupFrame(accList);
                frame.setTitle("Signup Form");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 360);
                frame.setResizable(false);
                frame.addWindowListener(new UpdateTitleAdapter());
            }
        }
    }

    // listener class for the button to open a list of accounts
    private class AccountListButtonListener implements ActionListener {

        // EFFECTS:  opens an AccountListFrame and lists the active accounts
        @Override
        public void actionPerformed(ActionEvent e) {
            AccountListFrame frame = new AccountListFrame(accList);
            frame.setTitle("AccountList");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 360);
            frame.setResizable(false);
        }
    }

    // class to update the title with user details on window close
    private class UpdateTitleAdapter extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            updateTitle();
        }
    }
}
