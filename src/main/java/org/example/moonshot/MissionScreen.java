package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.example.moonshot.models.Mission;
import org.example.moonshot.models.CrewMember;
import java.io.File;

public class MissionScreen extends JPanel {
    private JButton backButton;
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JTextArea descriptionArea;
    private JLabel imageLabel;
    private MainScreen mainScreen;
    private JPanel crewPanel;
    private JScrollPane descriptionScrollPane;
    private JScrollPane crewScrollPane;
    private JSplitPane splitPane;

    public MissionScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 40));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(20, 20, 40));

        backButton = new JButton("\u2190 Back");
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainScreen.showMissionPanel();
            }
        });
        topPanel.add(backButton, BorderLayout.WEST);

        titleLabel = new JLabel();
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Center align
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 75));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel missionInfoPanel = new JPanel(new GridBagLayout());
        missionInfoPanel.setBackground(new Color(20, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 20, 10);
        missionInfoPanel.add(imageLabel, gbc);

        JSeparator separator1 = new JSeparator();
        separator1.setForeground(new Color(189, 189, 189));
        separator1.setPreferredSize(new Dimension(1, 5));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        missionInfoPanel.add(separator1, gbc);

        JLabel highlightsLabel = new JLabel("Mission Highlights");
        highlightsLabel.setForeground(Color.WHITE);
        highlightsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        missionInfoPanel.add(highlightsLabel, gbc);

        dateLabel = new JLabel();
        dateLabel.setForeground(Color.LIGHT_GRAY);
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        missionInfoPanel.add(dateLabel, gbc);

        descriptionArea = new JTextArea();
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(new Color(20, 20, 40));
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 1, true));
        descriptionArea.setMargin(new Insets(5, 10, 5, 5));
        descriptionScrollPane = new JScrollPane(descriptionArea);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        missionInfoPanel.add(descriptionScrollPane, gbc);

        JSeparator separator2 = new JSeparator();
        separator2.setForeground(new Color(189, 189, 189));
        separator2.setPreferredSize(new Dimension(1, 5));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        missionInfoPanel.add(separator2, gbc);

        JLabel crewLabel = new JLabel("Crew");
        crewLabel.setForeground(Color.WHITE);
        crewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        missionInfoPanel.add(crewLabel, gbc);

        crewPanel = new JPanel();
        crewPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        crewPanel.setBackground(new Color(20, 20, 40));
        crewScrollPane = new JScrollPane(crewPanel);
        crewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        crewScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        missionInfoPanel.add(crewScrollPane, gbc);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, missionInfoPanel, crewScrollPane);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerSize(0);
        add(splitPane, BorderLayout.CENTER);
    }

    public void updateMissionDetails(Mission mission) {
        if (mission == null) {
            titleLabel.setText("Mission details unavailable");
            dateLabel.setText("Unknown Date");
            descriptionArea.setText("No details available for this mission.");
            imageLabel.setIcon(null);
            crewPanel.removeAll();
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

        crewPanel.removeAll();
        int buttonWidth = 200;
        int buttonHeight = 100;
        int padding = 10;
        int totalWidth = (buttonWidth + padding) * mission.getCrew().size();
        crewPanel.setPreferredSize(new Dimension(totalWidth, buttonHeight + 2 * padding));

        for (CrewMember crewMember : mission.getCrew()) {
            JButton crewButton = new JButton();
            crewButton.setLayout(new BorderLayout());
            crewButton.setBackground(new Color(30, 30, 50));
            crewButton.setForeground(Color.WHITE);
            crewButton.setFont(new Font("Arial", Font.PLAIN, 16));
            crewButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            crewButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            crewButton.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 1, true));

            JLabel crewImageLabel = new JLabel();
            String crewImagePath = "src/main/resources/Images/" + crewMember.getName() + ".imageset/" + crewMember.getName() + "@2x.jpg";
            if (new File(crewImagePath).exists()) {
                ImageIcon crewImage = new ImageIcon(crewImagePath);
                Image scaledCrewImage = crewImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                crewImageLabel.setIcon(new RoundImageIcon(scaledCrewImage, 25));
            } else {
                crewImageLabel.setText("No Image");
                crewImageLabel.setForeground(Color.RED);
            }
            crewImageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            String crewName = crewMember.getName().substring(0, 1).toUpperCase() + crewMember.getName().substring(1).toLowerCase();
            JLabel crewNameLabel = new JLabel(crewName);
            crewNameLabel.setForeground(Color.WHITE);
            crewNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            crewNameLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            crewButton.add(crewImageLabel, BorderLayout.WEST);
            crewButton.add(crewNameLabel, BorderLayout.CENTER);

            crewButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mainScreen.showAstronautDetail(crewMember, mission);
                }
            });

            crewPanel.add(crewButton);
        }
        crewPanel.revalidate();
        crewPanel.repaint();


        descriptionScrollPane.getVerticalScrollBar().setValue(0);
        crewScrollPane.getHorizontalScrollBar().setValue(0);
    }
}