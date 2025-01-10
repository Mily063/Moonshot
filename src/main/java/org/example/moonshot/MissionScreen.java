package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.example.moonshot.models.Mission;

public class MissionScreen extends JFrame {
    public MissionScreen(List<Mission> missions) {
        setTitle("Moonshot - Mission Viewer");
        setSize(1200, 800); // Adjust window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a darker background color
        getContentPane().setBackground(new Color(20, 20, 40));

        // Create a panel to hold the mission tiles
        JPanel missionPanel = new JPanel();
        missionPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Use GridLayout with two columns
        missionPanel.setBackground(new Color(20, 20, 40));

        // Add tiles for each mission
        for (Mission mission : missions) {
            missionPanel.add(new MissionTile(mission));
        }

        // Wrap the mission panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(missionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the scroll pane to the frame
        add(scrollPane);
    }
}