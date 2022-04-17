module hu.filmest.filmest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens hu.filmest.filmest to javafx.fxml, com.google.gson;
    exports hu.filmest.filmest;
    exports hu.filmest.filmest.controllers;
    opens hu.filmest.filmest.controllers to com.google.gson,javafx.fxml;
    opens hu.filmest.filmest.classes to com.google.gson, javafx.fxml;
    opens hu.filmest.filmest.pivot to com.google.gson, javafx.fxml;
}