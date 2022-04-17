package hu.filmest.filmest.controllers;

import hu.filmest.filmest.Controller;
import hu.filmest.filmest.Api;
import hu.filmest.filmest.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class RendezoModositas extends Controller {
    @FXML
    public TextField modositRendezo;
    private Rendezo modositando;
    public void initialize(){
        modositRendezo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                modositRendezo.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }
    public Rendezo getModositando() {
        return modositando;
    }

    public void setModositando(Rendezo modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    public void onModositButtonClick(ActionEvent actionEvent) {
        String rendezo=modositRendezo.getText().trim();
        if (rendezo.isEmpty()){
            alert("Rendező megadása kötelező");
            return;
        }
        modositando.setRendezoNev(rendezo);
        try {
            List<Rendezo> rendezoList= Api.getRendezokList();
            for (Rendezo r:rendezoList
            ) {
                if (r.getRendezoNev().equalsIgnoreCase(rendezo)){
                    alert("A rendező már létezik");

                    return;
                }
            }
            Rendezo modositott = Api.rendezoModositas(modositando);
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
        modositRendezo.setText(modositando.getRendezoNev());
    }
}
