package org.example.moonshot;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.example.moonshot.models.Mission;
import java.util.Date;

public class MissionTile extends JPanel {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public MissionTile(Mission mission) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 50));
        setBorder(new RoundedBorder(15)); // Set rounded border with radius 15
        setPreferredSize(new Dimension(200, 300)); // Adjust tile size

        // Mission name
        JLabel nameLabel = new JLabel("Apollo " + mission.getId());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Load mission image
        String imagePath = "src/main/resources/Images/apollo" + mission.getId() + ".imageset/apollo" + mission.getId() + "@2x.png";
        ImageIcon missionImage = new ImageIcon(imagePath);
        Image scaledImage = missionImage.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Mission number
        JLabel numberLabel = new JLabel("Mission #" + mission.getId());
        numberLabel.setForeground(Color.WHITE);
        numberLabel.setHorizontalAlignment(JLabel.CENTER);
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        numberLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Mission launch date
        String launchDateString = mission.getLaunchDate();
        Date launchDate = null;
        String formattedDate = "Unknown";
        if (launchDateString != null) {
            try {
                launchDate = DATE_FORMAT.parse(launchDateString);
                formattedDate = DATE_FORMAT.format(launchDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        JLabel dateLabel = new JLabel(formattedDate);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Add components
        add(nameLabel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);
        add(numberLabel, BorderLayout.SOUTH);
        add(dateLabel, BorderLayout.SOUTH);
    }

    // Custom border class for rounded corners
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = radius + 1;
            return insets;
        }
    }
}