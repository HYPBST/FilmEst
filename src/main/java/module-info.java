module hu.filmest.filmest {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.filmest.filmest to javafx.fxml;
    exports hu.filmest.filmest;
}