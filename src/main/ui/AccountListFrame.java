package ui;

import model.AccountList;
import model.UserAccount;

import javax.swing.*;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

// this class represents a frame where the list of active account are shown
public class AccountListFrame extends JFrame {

    JScrollPane pane;
    private AccountList accList;

    // EFFECTS:  copies the accList from the paramater and sets up the list
    public AccountListFrame(AccountList accList) {
        this.accList = accList;
        setList();
    }

    // EFFECTS:  puts the accList into a JList, then onto a JScrollPanel for the user to view
    public void setList() {
        Map<String, UserAccount> data = accList.getList();
        TreeSet<String> listData = new TreeSet<>(new CompareLeaderboard());
        for (Map.Entry<String, UserAccount> entry : data.entrySet()) {
            listData.add(entry.getKey());
        }

        JList<Object> list = new JList<>(listData.toArray());
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        pane = new JScrollPane(list);
        this.add(pane);
    }

    // this class is a comparator for the treeset
    class CompareLeaderboard implements Comparator<String> {

        // EFFECTS:  compares in terms of Strings
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }

}