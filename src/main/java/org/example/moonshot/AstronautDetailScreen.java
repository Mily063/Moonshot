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
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private Mission mission;

    public AstronautDetailScreen(MainScreen mainScreen) {
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 40));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(20, 20, 40));

        JButton backButton = new JButton("\u2190 Back");
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

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(20, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(imageLabel, gbc);

        nameLabel = new JLabel();
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        centerPanel.add(nameLabel, gbc);

        roleLabel = new JLabel();
        roleLabel.setForeground(Color.LIGHT_GRAY);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        roleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        centerPanel.add(roleLabel, gbc);

        descriptionArea = new JTextArea();
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(new Color(20, 20, 40));
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        centerPanel.add(descriptionScrollPane, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void updateAstronautDetails(CrewMember crewMember, Mission mission) {
        this.mission = mission;
        nameLabel.setText(getAstronautName(crewMember.getName()));
        roleLabel.setText(crewMember.getRole());
        String description = getAstronautDescription(crewMember.getName());
        descriptionArea.setText(description);

        String imagePath = "src/main/resources/Images/" + crewMember.getName() + ".imageset/" + crewMember.getName() + "@2x.jpg";
        if (new File(imagePath).exists()) {
            ImageIcon astronautImage = new ImageIcon(imagePath);
            Image scaledImage = astronautImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new RoundImageIcon(scaledImage, 50)); // Use RoundImageIcon with corner radius
        } else {
            imageLabel.setIcon(null);
        }
    }

    private String getAstronautName(String astronautId) {
        try (FileReader reader = new FileReader("src/main/resources/astronauts.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
            Map<String, Map<String, String>> astronauts = gson.fromJson(reader, type);
            if (astronauts.containsKey(astronautId)) {
                return astronauts.get(astronautId).get("name");
            } else {
                return "Name not available.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading name.";
        }
    }

    private String getAstronautDescription(String astronautId) {
        try (FileReader reader = new FileReader("src/main/resources/astronauts.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
            Map<String, Map<String, String>> astronauts = gson.fromJson(reader, type);
            if (astronauts.containsKey(astronautId)) {
                return astronauts.get(astronautId).get("description");
            } else {
                return "Description not available.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading description.";
        }
    }
}