package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import org.example.moonshot.models.Mission;

public class MissionTile extends JPanel {
    public MissionTile(Mission mission) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 50));
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        setPreferredSize(new Dimension(200, 300)); // Adjust tile size

        // Load mission image
                String imagePath = "src/main/resources/Images/" + "apollo"+ mission.getId() + ".imageset/" + mission.getId() + "@2x.png";
        ImageIcon missionImage = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(missionImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Mission name and year
        String launchYear = (mission.getLaunchDate() != null) ? mission.getLaunchDate().substring(0, 4) : "Unknown";
        JLabel titleLabel = new JLabel(mission.getId() + ": " + launchYear);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components
        add(imageLabel, BorderLayout.CENTER);
        add(titleLabel, BorderLayout.SOUTH);
    }
}