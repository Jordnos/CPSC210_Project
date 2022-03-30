package ui;

import model.Leaderboard;

import javax.swing.*;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

// this class represents a frame where the list of scores and users
public class LeaderboardFrame extends JFrame {

    JScrollPane pane;
    private Leaderboard leaderboard;

    // EFFECTS:  copies the leaderboard from the paramater and sets up the list
    public LeaderboardFrame(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
        setLeaderboard();
    }

    // EFFECTS:  puts the leaderboard into a JList, then onto a JScrollPanel for the user to view
    public void setLeaderboard() {
        Map<String, Integer> data = leaderboard.getScoresList();
        TreeSet<String> listData = new TreeSet<>(new CompareLeaderboard());
        for (Map.Entry<String,Integer> entry : data.entrySet()) {
            listData.add(entry.getValue() + "     " + entry.getKey());
        }

        JList<Object> list = new JList<>(listData.toArray());
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        pane = new JScrollPane(list);
        this.add(pane);
    }

    // this class is a comparator for the treeset
    class CompareLeaderboard implements Comparator<String> {

        // EFFECTS:  compares the strings based on the scores at the front, largest to smallest
        @Override
        public int compare(String str1, String str2) {
            return getScore(str2) - getScore(str1);
        }

        // EFFECTS:  gets the integer from the front of the given string and returns it
        private int getScore(String str) {
            int i = 0;
            while (str.charAt(i) != ' ') {
                i++;
            }
            return Integer.parseInt(str.substring(0,i));
        }
    }

}