package hu.filmest.filmest.controllers;

import hu.filmest.filmest.Controller;
import hu.filmest.filmest.Api;
import hu.filmest.filmest.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class KategoriaFelvetel extends Controller {
    @FXML
    private TextField felvetelKategoria;
    public void initialize(){
        felvetelKategoria.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                felvetelKategoria.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }
    public void onFelvetelButtonClick(ActionEvent actionEvent) {
        String kategoria=felvetelKategoria.getText().trim();
        if(kategoria.isEmpty()){
            alert("A kategória megadása kötelező");
            return;
        }

        try {
            List<Kategoria> kategoriaList= Api.getKategoriaList();
            for (Kategoria k:kategoriaList
            ) {
                if (k.getKategoria().equalsIgnoreCase(kategoria)){
                    alert("A kategória már létezik");

                    return;
                }
            }
            Kategoria ujKategoria =new Kategoria(kategoria);
            Kategoria letrehozott= Api.kategoriaHozzaadas(ujKategoria);
            if (letrehozott != null ){
                alert("Kategória hozzáadása sikeres");
                this.stage.close();
            } else {
                alert("Kategória hozzáadása sikeretelen");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
