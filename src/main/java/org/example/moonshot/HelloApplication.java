package org.example.moonshot;

import javax.swing.*;
import java.util.List;
import org.example.moonshot.models.Mission;
import org.example.moonshot.utils.DataLoader;

public class HelloApplication {
    public static void main(String[] args) {
        // Wczytanie danych z JSON
        List<Mission> missions = DataLoader.loadMissions("src/main/resources/missions.json");

        // Uruchomienie aplikacji Swing
        SwingUtilities.invokeLater(() -> {
            if (missions != null) {
                new HelloController(missions);
            } else {
                JOptionPane.showMessageDialog(null, "Nie udało się wczytać danych JSON.", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
