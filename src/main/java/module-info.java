module hu.filmest.filmest {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.filmest.filmest to javafx.fxml;
    exports hu.filmest.filmest;
    exports hu.filmest.filmest.controllers;
    opens hu.filmest.filmest.controllers to javafx.fxml;
}