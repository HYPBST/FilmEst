package hu.filmest.filmest.controllers;

import hu.filmest.filmest.Api;
import hu.filmest.filmest.Controller;
import hu.filmest.filmest.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmModositas extends Controller {
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
    private Film modositando;
    private List<Kategoria> kategoriaListUj;
    private List<Rendezo> rendezoListUj;
    private List<Szinesz> szineszListUj;
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
    }

    public Film getModositando() {
        return modositando;
    }

    public void setModositando(Film modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        txtCim.setText(modositando.getCim());
        txtLeiras.setText(modositando.getLeiras());
        txtEv.setText(String.valueOf( modositando.getMegjelenesiEv()));
        txtErtekeles.setText(String.valueOf( modositando.getErtekeles()));
        txtImgUrl.setText(modositando.getImageUrl());
        List<Kategoria> kategoriaList = modositando.getKategoriak();
        List<Szinesz> szineszList = modositando.getSzineszek();
        List<Rendezo> rendezoList = modositando.getRendezok();
        ArrayList<CustomMenuItem> kategoriak=new ArrayList<>();
        ArrayList<CustomMenuItem> szineszek=new ArrayList<>();
        ArrayList<CustomMenuItem> rendezok=new ArrayList<>();
        try {
            for (Kategoria k: Api.getKategoriaList()
            ) {
                boolean bennevan=false;
                for (Kategoria kat: kategoriaList
                ) {
                    if (kat.getKategoria().equalsIgnoreCase(k.getKategoria())){
                        CheckBox cb=new CheckBox(k.getKategoria());
                        cb.setSelected(true);
                        cb.setUserData(kat);
                        CustomMenuItem item=new CustomMenuItem(cb);
                        item.setUserData(cb);
                        item.setText(k.getKategoria());
                        item.setHideOnClick(false);
                        kategoriak.add(item);
                        bennevan=true;
                    }
                }
                if(!bennevan){
                    CheckBox cb=new CheckBox(k.getKategoria());
                    cb.setUserData(k);
                    CustomMenuItem item=new CustomMenuItem(cb);
                    item.setUserData(cb);
                    item.setText(k.getKategoria());
                    item.setHideOnClick(false);
                    kategoriak.add(item);
                }
            }
            for (Szinesz sz:Api.getSzineszekList()
            ) {
                boolean bennevan=false;
                for (Szinesz szin: szineszList
                ) {
                    if (szin.getSzineszNev().equalsIgnoreCase(sz.getSzineszNev())){
                        CheckBox cb=new CheckBox(sz.getSzineszNev());
                        cb.setSelected(true);
                        cb.setUserData(szin);
                        CustomMenuItem item=new CustomMenuItem(cb);
                        item.setUserData(cb);
                        item.setText(sz.getSzineszNev());
                        item.setHideOnClick(false);
                        szineszek.add(item);
                        bennevan=true;
                    }
                }
                if(!bennevan){
                    CheckBox cb=new CheckBox(sz.getSzineszNev());
                    cb.setUserData(sz);
                    CustomMenuItem item=new CustomMenuItem(cb);
                    item.setUserData(cb);
                    item.setText(sz.getSzineszNev());
                    item.setHideOnClick(false);
                    szineszek.add(item);
                }
            }
            for (Rendezo r:Api.getRendezokList()
            ) {
                boolean bennevan=false;
                for (Rendezo ren: rendezoList
                ) {
                    if (ren.getRendezoNev().equalsIgnoreCase(r.getRendezoNev())){
                        CheckBox cb=new CheckBox(r.getRendezoNev());
                        cb.setSelected(true);
                        cb.setUserData(ren);
                        CustomMenuItem item=new CustomMenuItem(cb);
                        item.setUserData(cb);
                        item.setText(r.getRendezoNev());
                        item.setHideOnClick(false);
                        rendezok.add(item);
                        bennevan=true;
                    }
                }
                if (!bennevan){
                    CheckBox cb=new CheckBox(r.getRendezoNev());
                    cb.setUserData(r);
                    CustomMenuItem item=new CustomMenuItem(cb);
                    item.setUserData(cb);
                    item.setText(r.getRendezoNev());
                    item.setHideOnClick(false);
                    rendezok.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuKategoria.getItems().setAll(kategoriak);
        menuSzineszek.getItems().setAll(szineszek);
        menuRendezok.getItems().setAll(rendezok);

    }


    @FXML
    public void onModositButtonClick(ActionEvent actionEvent) {
        String cim=txtCim.getText();
        String leiras=txtLeiras.getText();
        String ev=txtEv.getText();
        String ertekeles=txtErtekeles.getText();
        String url=txtImgUrl.getText();
        kategoriaListUj=new ArrayList<>();
        rendezoListUj=new ArrayList<>();
        szineszListUj=new ArrayList<>();
        if (cim.isEmpty()){
            alert("Cím megadása kötelező");
            return;
        }
        boolean valodiUrl=false;
        if (url.startsWith("www.")|| url.startsWith("https://")){
            valodiUrl=true;
        }
        if (!valodiUrl){
            alert("Valódi URL-t kell megadni.");
            return;
        }
        if (leiras.isEmpty()){
            alert("Leírás megadása kötelező");
            return;
        }
        if (ev.isEmpty()){
            alert("Év megadása kötelező");
            return;
        }
        if (ertekeles.isEmpty()){
            alert("Értékelés megadása kötelező");
            return;
        }
        if (url.isEmpty()){
            alert("Url megadása kötelező");
            return;
        }


        modositando.setCim(cim);
        modositando.setErtekeles(Integer.parseInt(ertekeles));
        modositando.setLeiras(leiras);
        modositando.setMegjelenesiEv(Integer.parseInt(ev));
        modositando.setImageUrl(url);
        for (MenuItem mi:menuKategoria.getItems()) {
            CheckBox cb = (CheckBox)mi.getUserData() ;
            Kategoria kategoria = (Kategoria)cb.getUserData();
            if (cb.isSelected()){
                kategoriaListUj.add(kategoria);

            }

        }
        for (Kategoria kat:kategoriaListUj
        ) {
            System.out.println(kat.getId());
            System.out.println(kat.getKategoria());
        }
        modositando.setKategoriak(kategoriaListUj);
        for (MenuItem mi:menuRendezok.getItems()) {
            CheckBox cb = (CheckBox)mi.getUserData() ;
            Rendezo rendezo = (Rendezo)cb.getUserData();
            if (cb.isSelected()){
                rendezoListUj.add(rendezo);
            }

        }
        modositando.setRendezok(rendezoListUj);
        for (MenuItem mi:menuSzineszek.getItems()) {
            CheckBox cb = (CheckBox)mi.getUserData() ;
            Szinesz szinesz = (Szinesz) cb.getUserData();
            if (cb.isSelected()){
                szineszListUj.add(szinesz);
            }

        }
        modositando.setSzineszek(szineszListUj);
        if (rendezoListUj.size()>1){
            alert("Csak egy rendezőt lehet beállítani.");
            return;
        }
        if(rendezoListUj.isEmpty()){
            alert("Meg kell adni rendezőt.");
            return;
        }
        if(kategoriaListUj.isEmpty()){
            alert("Meg kell adni kategóriát/kategóriákat.");
            return;
        }
        if(szineszListUj.isEmpty()){
            alert("Meg kell adni színészeket.");
            return;
        }
        try {
            Film modositott = Api.filmModositasa(modositando);
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
}
