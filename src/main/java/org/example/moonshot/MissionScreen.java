package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.example.moonshot.models.Mission;

public class MissionScreen extends JPanel {
    private JLabel backLabel;
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JTextArea descriptionArea;
    private JLabel imageLabel;
    private MainScreen mainScreen;

    public MissionScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 40));

        // Back label
        backLabel = new JLabel("<back");
        backLabel.setForeground(Color.BLUE);
        backLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainScreen.showMissionPanel();
            }
        });

        // Mission title
        titleLabel = new JLabel();
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10)); // Add padding
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 55)); // Add left padding to shift left

        // Mission logo
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Darker gray separator line with padding
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(105, 105, 105)); // Darker gray color
        separator.setPreferredSize(new Dimension(1, 1)); // Set height to 1 pixel

        JPanel separatorPanel = new JPanel(new BorderLayout());
        separatorPanel.setBackground(new Color(20, 20, 40));
        separatorPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50)); // 5% padding on left and right
        separatorPanel.add(separator, BorderLayout.CENTER);

        // Mission Highlights label
        JLabel highlightsLabel = new JLabel("Mission Highlights");
        highlightsLabel.setForeground(Color.WHITE);
        highlightsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        highlightsLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adjust padding

        // Mission launch date
        dateLabel = new JLabel();
        dateLabel.setForeground(Color.LIGHT_GRAY);
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Add padding

        // Mission description
        descriptionArea = new JTextArea();
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(new Color(20, 20, 40));
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Add components to the panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(20, 20, 40));
        topPanel.add(backLabel, BorderLayout.WEST);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(20, 20, 40));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        topPanel.add(titlePanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(20, 20, 40));
        centerPanel.add(imageLabel, BorderLayout.NORTH);
        centerPanel.add(separatorPanel, BorderLayout.CENTER);

        JPanel highlightsPanel = new JPanel(new BorderLayout());
        highlightsPanel.setBackground(new Color(20, 20, 40));
        highlightsPanel.add(highlightsLabel, BorderLayout.NORTH);
        highlightsPanel.add(dateLabel, BorderLayout.CENTER);
        highlightsPanel.add(new JScrollPane(descriptionArea), BorderLayout.SOUTH);

        centerPanel.add(highlightsPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void updateMissionDetails(Mission mission) {
        if (mission == null) {
            titleLabel.setText("Mission details unavailable");
            dateLabel.setText("Unknown Date");
            descriptionArea.setText("No details available for this mission.");
            imageLabel.setIcon(null);
            return;
        }

        String imagePath = "src/main/resources/Images/apollo" + mission.getId() + ".imageset/apollo" + mission.getId() + "@2x.png";
        ImageIcon missionImage = new ImageIcon(imagePath);
        Image scaledImage = missionImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        String missionName = mission.getName() != null ? mission.getName() : String.valueOf(mission.getId());
        titleLabel.setText("Apollo " + missionName);

        String launchDate = mission.getLaunchDate() != null ? mission.getLaunchDate() : "Unknown Date";
        dateLabel.setText(launchDate);

        descriptionArea.setText(mission.getDescription());
    }
}