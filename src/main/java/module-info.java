module hu.filmest.filmest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires java.desktop;


    opens hu.filmest.filmest to javafx.fxml, com.google.gson;
    exports hu.filmest.filmest;
    exports hu.filmest.filmest.controllers;
    exports hu.filmest.filmest.classes;
    exports hu.filmest.filmest.pivot;
    opens hu.filmest.filmest.controllers to com.google.gson,javafx.fxml;
    opens hu.filmest.filmest.classes to com.google.gson, javafx.fxml;
    opens hu.filmest.filmest.pivot to com.google.gson, javafx.fxml;
}