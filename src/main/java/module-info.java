module org.example.moonshot {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.moonshot to javafx.fxml;
    exports org.example.moonshot;
}