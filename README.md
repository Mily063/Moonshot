# Project Overview

## Moonshot-Inspired Java Application

This project is a graphical application developed in Java, inspired by the **Moonshot** project from *Hacking with Swift*. The goal was to recreate and adapt the experience of the original Swift-based project using Java technologies while maintaining its core functionalities and design aesthetics.

## Features
- **Graphical Interface** – A modern UI built using Java's graphical libraries.
- **Educational Content** – Displays historical information and details inspired by space missions.
- **Interactive Experience** – Users can explore different missions, view images, and read descriptions.
- **Optimized Performance** – Ensures smooth rendering and efficient data handling.

## Technologies Used
- **Java 21** – Core programming language.
- **JavaFX** – For building the graphical user interface.
- **Gson** – To parse and retrieve mission and astronaut data from JSON.
- **Maven** – Build and dependency management.

## Team and my contribution

This was a two-person university project created by **Miłosz Gibała** and **Maciej**. Work was conducted in parallel with tasks divided cleanly across distinct application screens, data layers, and assets:

- **Miłosz Gibała** (my contribution):
  - **Project Setup & Build**: Initialized the project structure, configured Maven (`pom.xml`), JavaFX plugin, and Maven Wrapper (`mvnw`).
  - **Asset Management**: Sourced and organized all visual media (~54 MB) for Apollo missions and astronaut portraits under `src/main/resources/Images/`.
  - **Main Screen & Mission View**: Developed `MainScreen` and `MissionTile` (grid presentation of missions), `RoundImageIcon` utility, and the full `MissionScreen` (mission overview, badge icon, launch date, description, and crew roster).
  - **Initial Documentation**: Created the project `README.md`.

- **Maciej**:
  - **Data Models & Loading**: Implemented `Mission` and `CrewMember` data models, the `DataLoader` utility using Google Gson, and structured JSON data sources (`missions.json`, `astronauts.json`).
  - **Astronaut Details Screen**: Built the entire `AstronautDetailScreen` displaying comprehensive astronaut biographies, portraits, and roles.
  - **Navigation & Fine-Tuning**: Connected cross-screen navigation between missions and astronaut profiles, refined screen styling, and designed the UML class diagram (`Diagram.puml`).

## Running it

The project uses Java 21 and the Maven Wrapper (`mvnw`), so a global Maven installation is not required.

### Prerequisites
- **JDK 21** or later installed and configured (`JAVA_HOME`).

### Run via Maven Wrapper

On Linux / macOS:
```bash
./mvnw clean javafx:run
```

On Windows:
```cmd
mvnw.cmd clean javafx:run
```

*(Alternatively, if you have Maven installed locally, you can run `mvn clean javafx:run`)*.

## Credits
Inspired by *Hacking with Swift's Moonshot* and adapted into Java with graphical enhancements.
