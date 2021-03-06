package hu.filmest.filmest.controllers;

import hu.filmest.filmest.Controller;
import hu.filmest.filmest.Api;
import hu.filmest.filmest.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmFelvetel extends Controller {
    @FXML
    public MenuButton menuKategoria;
    @FXML
    public MenuButton menuSzineszek;
    @FXML
    public MenuButton menuRendezok;
    @FXML
    private TextField txtCim;
    @FXML
    private TextArea txtLeiras;
    @FXML
    private TextField txtEv;
    @FXML
    private TextField txtErtekeles;
    @FXML
    private TextField txtImgUrl;

    ArrayList<CustomMenuItem> kategoriak=new ArrayList<>();
    ArrayList<CustomMenuItem> szineszek=new ArrayList<>();
    ArrayList<CustomMenuItem> rendezok=new ArrayList<>();
    public void initialize() {
        txtErtekeles.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtErtekeles.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtEv.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtEv.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        try {
            for (Kategoria k: Api.getKategoriaList()
            ) {
                CheckBox cb=new CheckBox(k.getKategoria());
                cb.setUserData(k);
                CustomMenuItem item=new CustomMenuItem(cb);
                item.setUserData(cb);
                item.setText(k.getKategoria());
                item.setHideOnClick(false);
                kategoriak.add(item);
            }

            for (Szinesz sz:Api.getSzineszekList()
            ) {

                CheckBox cb=new CheckBox(sz.getSzineszNev());
                cb.setUserData(sz);
                CustomMenuItem item=new CustomMenuItem(cb);
                item.setUserData(cb);
                item.setText(sz.getSzineszNev());
                item.setHideOnClick(false);
                szineszek.add(item);

            }
            for (Rendezo r:Api.getRendezokList()
            ) {

                CheckBox cb=new CheckBox(r.getRendezoNev());
                cb.setUserData(r);
                CustomMenuItem item=new CustomMenuItem(cb);
                item.setUserData(cb);
                item.setText(r.getRendezoNev());
                item.setHideOnClick(false);
                rendezok.add(item);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuKategoria.getItems().setAll(kategoriak);
        menuSzineszek.getItems().setAll(szineszek);
        menuRendezok.getItems().setAll(rendezok);
    }

    @FXML
    public void onFelvetelButtonClick(ActionEvent actionEvent){
        List<Kategoria> kategoriaList = new ArrayList<>();
        List<Rendezo> rendezoList = new ArrayList<>();
        List<Szinesz> szineszList = new ArrayList<>();
        String cim=txtCim.getText();
        try {
            for (Film f:Api.getFilmList()
            ) {
                if (cim.equalsIgnoreCase(f.getCim())){
                    alert("Ilyen c??mmel l??tezik m??r film.");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String leiras=txtLeiras.getText();
        String ev= txtEv.getText();
        String ert= txtErtekeles.getText();
        String imageUrl=txtImgUrl.getText();
        ArrayList<CustomMenuItem> kategoriak=new ArrayList<>();
        ArrayList<CustomMenuItem> szineszek=new ArrayList<>();
        ArrayList<CustomMenuItem> rendezok=new ArrayList<>();
        if (cim.isEmpty()){
            alert("C??m megad??sa k??telez??");
            return;
        }
        if (leiras.isEmpty()){
            alert("Le??r??s megad??sa k??telez??");
            return;
        }
        if (ev.isEmpty()){
            alert("??v megad??sa k??telez??");
            return;
        }
        if (ert.isEmpty()){
            alert("??rt??kel??s megad??sa k??telez??");
            return;
        }
        if (imageUrl.isEmpty()){
            alert("Url megad??sa k??telez??");
            return;
        }
        boolean valodiUrl= imageUrl.startsWith("www.") || imageUrl.startsWith("https://");
        if (!valodiUrl){
            alert("Val??di URL-t kell megadni.");
            return;
        }
        int megjelenesiEv= Integer.parseInt(ev);
        int ertekeles= Integer.parseInt(ert);
        if (ertekeles<=0||ertekeles>10){
            alert("Az ??rt??kel??snek 1 ??s 10 k??z??tt kell lennie!");
            return;
        }
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy");
        Date date = new Date(System.currentTimeMillis());
        int jelenlegiEv= Integer.parseInt(formatter.format(date));
        if (megjelenesiEv<1888||megjelenesiEv>jelenlegiEv){
            alert("A megjelen??si ??vnek val??snak kell lennie!");
            return;
        }
        Film ujFilm = new Film( 0,cim, leiras,megjelenesiEv,ertekeles,imageUrl);
        for (MenuItem mi:menuKategoria.getItems()) {
            CheckBox cb = (CheckBox)mi.getUserData() ;
            Kategoria kategoria = (Kategoria)cb.getUserData();
            if (cb.isSelected()){
                kategoriaList.add(kategoria);
            }

        }
        ujFilm.setKategoriak(kategoriaList);

        for (MenuItem mi:menuRendezok.getItems()) {
            CheckBox cb = (CheckBox)mi.getUserData() ;
            Rendezo rendezo = (Rendezo)cb.getUserData();
            if (cb.isSelected()){
                rendezoList.add(rendezo);
            }

        }

        ujFilm.setRendezok(rendezoList);

        for (MenuItem mi:menuSzineszek.getItems()) {
            CheckBox cb = (CheckBox)mi.getUserData() ;
            Szinesz szinesz = (Szinesz) cb.getUserData();
            if (cb.isSelected()){
                szineszList.add(szinesz);
            }
        }
        ujFilm.setSzineszek(szineszList);

        if (rendezoList.size()>1){
            alert("Csak egy rendez??t lehet be??ll??tani.");
            return;
        }
        if(rendezoList.isEmpty()){
            alert("Meg kell adni rendez??t.");
            return;
        }
        if(kategoriaList.isEmpty()){
            alert("Meg kell adni kateg??ri??t/kateg??ri??kat.");
            return;
        }
        if(szineszList.isEmpty()){
            alert("Meg kell adni sz??n??szeket.");
            return;
        }

        try {

            Film letrehozott = Api.filmHozzaadasa(ujFilm);
            if (letrehozott != null ){
                alert("Film hozz??ad??sa sikeres");
                this.stage.close();
            } else {
                alert("Film hozz??ad??sa sikeretelen");
            }
        } catch (Exception e) {
            exceptionAlert(e);
        }

    }
}
