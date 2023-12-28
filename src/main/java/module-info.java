module com.example.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens views to javafx.fxml;
    opens app to javafx.fxml;
    opens fragments to javafx.fxml;

    exports app;
    exports views;
    exports util;
}