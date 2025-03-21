package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.example.moonshot.models.Mission;
import org.example.moonshot.models.CrewMember;

public class MainScreen extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MissionScreen missionDetailScreen;
    private AstronautDetailScreen astronautDetailScreen;

    public MainScreen(List<Mission> missions) {
        setTitle("Moonshot - Mission Viewer");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel missionPanel = new JPanel();
        missionPanel.setLayout(new GridLayout(0, 2, 10, 10));
        missionPanel.setBackground(new Color(20, 20, 40));

        for (Mission mission : missions) {
            missionPanel.add(new MissionTile(mission, this));
        }

        JScrollPane scrollPane = new JScrollPane(missionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scrollPane, "MissionPanel");
        missionDetailScreen = new MissionScreen(this);
        mainPanel.add(missionDetailScreen, "MissionDetailScreen");

        astronautDetailScreen = new AstronautDetailScreen(this);
        mainPanel.add(astronautDetailScreen, "AstronautDetailScreen");

        add(mainPanel);
    }

    public void showMissionDetail(Mission mission) {
        missionDetailScreen.updateMissionDetails(mission);
        cardLayout.show(mainPanel, "MissionDetailScreen");
    }

    public void showMissionPanel() {
        cardLayout.show(mainPanel, "MissionPanel");
    }

    public void showAstronautDetail(CrewMember crewMember, Mission mission) {
        astronautDetailScreen.updateAstronautDetails(crewMember, mission);
        cardLayout.show(mainPanel, "AstronautDetailScreen");
    }

}