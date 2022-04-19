package hu.filmest.filmest.controllers;

import hu.filmest.filmest.Controller;
import hu.filmest.filmest.Api;
import hu.filmest.filmest.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class SzineszFelvetel extends Controller {
    @FXML
    public TextField felvetelSzinesz;



    public void initialize(){
        felvetelSzinesz.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                felvetelSzinesz.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    public void onFelvetelButtonClick(ActionEvent actionEvent) {
        String szinesz=felvetelSzinesz.getText().trim();
        if(szinesz.isEmpty()){
            alert("A színész megadása kötelező");
            return;
        }

        try {
            List<Szinesz> szineszList= Api.getSzineszekList();
            for (Szinesz sz:szineszList
            ) {
                if (sz.getSzineszNev().equalsIgnoreCase(szinesz)){
                    alert("A színész már létezik");

                    return;
                }
            }
            Szinesz ujSzinesz =new Szinesz(szinesz);
            Szinesz letrehozott= Api.szineszHozzaadas(ujSzinesz);
            if (letrehozott != null ){
                alert("Színész hozzáadása sikeres");
                this.stage.close();
            } else {
                alert("Színész hozzáadása sikeretelen");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
