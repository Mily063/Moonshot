package org.example.moonshot;

import javax.swing.*;
import java.util.List;
import org.example.moonshot.models.Mission;
import org.example.moonshot.utils.DataLoader;

public class HelloApplication {
    public static void main(String[] args) {
        // Load data from JSON
        List<Mission> missions = DataLoader.loadMissions("src/main/resources/missions.json");

        // Start the Swing application
        SwingUtilities.invokeLater(() -> {
            if (missions != null) {
                new HelloController(missions);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to load JSON data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}