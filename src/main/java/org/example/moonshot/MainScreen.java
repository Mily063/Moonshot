package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.example.moonshot.models.Mission;

public class MainScreen extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel missionPanel;
    private MissionScreen missionDetailScreen;

    public MainScreen(List<Mission> missions) {
        setTitle("Moonshot - Mission Viewer");
        setSize(1200, 800); // Adjust window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Set a darker background color
        missionPanel = new JPanel();
        missionPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Use GridLayout with two columns
        missionPanel.setBackground(new Color(20, 20, 40));

        // Add tiles for each mission
        for (Mission mission : missions) {
            missionPanel.add(new MissionTile(mission, this));
        }

        // Wrap the mission panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(missionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scrollPane, "MissionPanel");
        missionDetailScreen = new MissionScreen();
        mainPanel.add(missionDetailScreen, "MissionDetailScreen");

        add(mainPanel);
    }

    public void showMissionDetail(Mission mission) {
        missionDetailScreen.updateMissionDetails(mission);
        cardLayout.show(mainPanel, "MissionDetailScreen");
    }

    public void showMissionPanel() {
        cardLayout.show(mainPanel, "MissionPanel");
    }
}