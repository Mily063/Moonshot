package org.example.moonshot;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.example.moonshot.models.Mission;

public class MissionScreen extends JFrame {
    public MissionScreen(List<Mission> missions) {
        setTitle("Moonshot - Mission Viewer");
        setSize(1200, 800); // Dostosuj rozmiar okna
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Użyj FlowLayout

        // Dodanie kafelków dla każdej misji
        for (Mission mission : missions) {
            add(new MissionTile(mission));
        }
    }
}