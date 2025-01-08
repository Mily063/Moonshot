package org.example.moonshot.models;

import java.util.List;

public class Mission {
    private int id;
    private String launchDate;
    private String description;
    private List<CrewMember> crew;

    // Gettery i settery
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLaunchDate() { return launchDate; }
    public void setLaunchDate(String launchDate) { this.launchDate = launchDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<CrewMember> getCrew() { return crew; }
    public void setCrew(List<CrewMember> crew) { this.crew = crew; }
}
