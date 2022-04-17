package hu.filmest.filmest.controllers;

import hu.filmest.filmest.Controller;
import hu.filmest.filmest.Api;
import hu.filmest.filmest.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class KategoriaModositas extends Controller {
    @FXML
    public TextField modositKategoria;

    private Kategoria modositando;
    public void initialize(){

        modositKategoria.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                modositKategoria.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }


    public Kategoria getModositando() {
        return modositando;
    }

    public void setModositando(Kategoria modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    public void onModositButtonClick(ActionEvent actionEvent) {
        String kategoria=modositKategoria.getText().trim();
        if (kategoria.isEmpty()){
            alert("Kategória megadása kötelező");
            return;
        }
        modositando.setKategoria(kategoria);
        try {
            List<Kategoria> kategoriaList= Api.getKategoriaList();
            for (Kategoria k:kategoriaList
            ) {
                if (k.getKategoria().equalsIgnoreCase(kategoria)){
                    alert("A kategória már létezik");

                    return;
                }
            }
            Kategoria modositott = Api.kategoriaModositas(modositando);
            if (modositott != null){
                alertWait("Sikeres módosítás");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ertekekBeallitasa() {
        modositKategoria.setText(modositando.getKategoria());
    }
}
