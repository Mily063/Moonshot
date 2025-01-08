module org.example.moonshot {
    requires java.desktop;
    requires com.google.gson;

    exports org.example.moonshot.models;
    opens org.example.moonshot.models to com.google.gson;
    opens org.example.moonshot to javafx.fxml;
    exports org.example.moonshot;
}