package hu.filmest.filmest.controllers;

import hu.filmest.filmest.Api;
import hu.filmest.filmest.Controller;
import hu.filmest.filmest.FilmEst;
import hu.filmest.filmest.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Controller{
    @FXML
    public TextField txtEmail;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public VBox bejelentkezesView;

    public void onButtonClickBejelentkezes(ActionEvent actionEvent) {

        String login_email = txtEmail.getText().trim();
        String login_password = txtPassword.getText().trim();

        Bejelentkezes bejelentkezes = new Bejelentkezes(login_email, login_password);

        try {
            Token token = Api.postBejelentkezes(bejelentkezes);
            Felhasznalo felhasznaloAdatok = Api.getBejelentkezesAdatok(token.getToken());
            System.out.println(felhasznaloAdatok.isAdmin());
            if(felhasznaloAdatok.isAdmin()==1) {
                ((Stage) bejelentkezesView.getScene().getWindow()).close();
                MainController main = (MainController) newWindow(
                        "main-view.fxml", "FilmEst", 800, 600
                );
                main.getStage().setResizable(false);
                main.getStage().show();
            }
            else{
                alert("Nincs jogod bejelentkezni.");
            }
        } catch (IOException e) {
            showAlert(e);
        }

        /*String login_email = txtEmail.getText().trim();
        String login_password = txtPassword.getText().trim();

        Bejelentkezes bejelentkezes = new Bejelentkezes(login_email, login_password);

        try {
            Token token = Api.postBejelentkezes(bejelentkezes);
            Felhasznalo felhasznaloAdatok = Api.getBejelentkezesAdatok(token.getToken());

            ((Stage) bejelentkezesView.getScene().getWindow()).close();
            MainController main = (MainController) newWindow(
                    "main-view.fxml", "Kreatív Ötletcentrum Raktárkezelő", 1300, 700
            );
            main.getStage().show();
        } catch (IOException e) {
            showAlert(e);
        }*/
    }

}
