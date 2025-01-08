package org.example.moonshot;

import javax.swing.*;
import java.util.List;
import org.example.moonshot.models.Mission;

public class HelloController {
    private final List<Mission> missions;

    public HelloController(List<Mission> missions) {
        this.missions = missions;
        showMissionScreen();
    }

    private void showMissionScreen() {
        JFrame missionScreen = new MissionScreen(missions);
        missionScreen.setVisible(true);
    }
}
