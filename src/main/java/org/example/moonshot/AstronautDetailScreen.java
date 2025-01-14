package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.moonshot.models.CrewMember;
import org.example.moonshot.models.Mission;

public class AstronautDetailScreen extends JPanel {
    private JTextArea descriptionArea;
    private JScrollPane descriptionScrollPane;
    private JLabel imageLabel;
    private JButton missionButton;
    private Mission mission;

    public AstronautDetailScreen(MainScreen mainScreen) {
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 40));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(20, 20, 40));

        JButton backButton = new JButton("\u2190 Back");
        backButton.setForeground(Color.WHITE); // Change color to white
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mission != null) {
                    mainScreen.showMissionDetail(mission);
                }
            }
        });
        topPanel.add(backButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(20, 20, 40));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(imageLabel, BorderLayout.NORTH);

        descriptionArea = new JTextArea();
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(new Color(20, 20, 40));
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionScrollPane = new JScrollPane(descriptionArea);
        centerPanel.add(descriptionScrollPane, BorderLayout.CENTER);

        missionButton = new JButton("Show Mission Details");
        missionButton.setForeground(Color.WHITE);
        missionButton.setBackground(new Color(30, 30, 50));
        missionButton.setFont(new Font("Arial", Font.PLAIN, 14));
        missionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        missionButton.setFocusPainted(false);
        missionButton.setContentAreaFilled(false);
        missionButton.setBorderPainted(false);
        missionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mission != null) {
                    mainScreen.showMissionDetail(mission);
                }
            }
        });
        centerPanel.add(missionButton, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void updateAstronautDetails(CrewMember crewMember, Mission mission) {
        this.mission = mission;
        String description = getAstronautDescription(crewMember.getName());
        descriptionArea.setText(description);

        String imagePath = "src/main/resources/Images/" + crewMember.getName() + ".imageset/" + crewMember.getName() + "@2x.jpg";
        if (new File(imagePath).exists()) {
            ImageIcon astronautImage = new ImageIcon(imagePath);
            Image scaledImage = astronautImage.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new RoundImageIcon(scaledImage, 50));
        } else {
            imageLabel.setText("No Image Available");
            imageLabel.setForeground(Color.RED);
        }
    }

    private String getAstronautDescription(String astronautName) {
        try (FileReader reader = new FileReader("src/main/resources/astronauts.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
            Map<String, Map<String, String>> astronauts = gson.fromJson(reader, type);
            if (astronauts.containsKey(astronautName)) {
                return astronauts.get(astronautName).get("description");
            } else {
                return "Description not available.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading description.";
        }
    }
}