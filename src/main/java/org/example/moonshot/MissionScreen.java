package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import org.example.moonshot.models.Mission;

public class MissionScreen extends JPanel {
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JTextArea descriptionArea;
    private JLabel imageLabel;

    public MissionScreen() {
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 40));

        // Mission logo
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Mission title
        titleLabel = new JLabel();
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Add padding

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
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(20, 20, 40));
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(dateLabel, BorderLayout.SOUTH);

        add(imageLabel, BorderLayout.NORTH);
        add(titlePanel, BorderLayout.CENTER);
        add(new JScrollPane(descriptionArea), BorderLayout.SOUTH);
    }

    public void updateMissionDetails(Mission mission) {
        String imagePath = "src/main/resources/Images/apollo" + mission.getId() + ".imageset/apollo" + mission.getId() + "@2x.png";
        ImageIcon missionImage = new ImageIcon(imagePath);
        Image scaledImage = missionImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        String missionName = mission.getName();
        titleLabel.setText(missionName != null ? missionName : "Unknown Mission");

        String launchDate = mission.getLaunchDate() != null ? mission.getLaunchDate() : "Unknown Date";
        dateLabel.setText(launchDate);

        descriptionArea.setText(mission.getDescription());
    }
}