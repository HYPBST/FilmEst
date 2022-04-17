package hu.filmest.filmest.classes;

import java.util.List;

public class Film {
    private int id;
    private String cim;
    private String leiras;
    private int megjelenesiEv;
    private int ertekeles;
    private String imageUrl;
    private List<Kategoria> kategoriak;
    private List<Rendezo> rendezok;
    private List<Szinesz> szineszek;


    public Film(int id, String cim, String leiras, int megjelenesiEv, int ertekeles, String imageUrl) {
        this.id = id;
        this.cim = cim;
        this.leiras = leiras;
        this.megjelenesiEv = megjelenesiEv;
        this.ertekeles = ertekeles;
        this.imageUrl = imageUrl;
    }
    public int getId() {
        return id;
    }


    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public int getMegjelenesiEv() {
        return megjelenesiEv;
    }

    public void setMegjelenesiEv(int megjelenesiEv) {
        this.megjelenesiEv = megjelenesiEv;
    }

    public int getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(int ertekeles) {
        this.ertekeles = ertekeles;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Kategoria> getKategoriak() {
        return kategoriak;
    }

    public void setKategoriak(List<Kategoria> kategoriak) {
        this.kategoriak = kategoriak;
    }

    public List<Rendezo> getRendezok() {
        return rendezok;
    }

    public void setRendezok(List<Rendezo> rendezok) {
        this.rendezok = rendezok;
    }

    public List<Szinesz> getSzineszek() {
        return szineszek;
    }

    public void setSzineszek(List<Szinesz> szineszek) {
        this.szineszek = szineszek;
    }

    public String kategoriakToString(){
        StringBuilder kimenet= new StringBuilder();
        for (int i = 0; i < this.kategoriak.size(); i++) {
            if (i!=this.kategoriak.size()-1){
                kimenet.append(this.kategoriak.get(i).getKategoria()).append(", ");
            }else{
                kimenet.append(this.kategoriak.get(i).getKategoria());
            }
        }
        return kimenet.toString();
    }
    public String rendezokToString(){
        StringBuilder kimenet= new StringBuilder();
        for (int i = 0; i < this.rendezok.size(); i++) {
            if (i!=this.rendezok.size()-1){
                kimenet.append(this.rendezok.get(i).getRendezoNev()).append(", ");
            }else{
                kimenet.append(this.rendezok.get(i).getRendezoNev());
            }
        }
        return kimenet.toString();
    }
    public String szineszekToString(){
        StringBuilder kimenet= new StringBuilder();
        for (int i = 0; i < this.szineszek.size(); i++) {
            if (i!=this.szineszek.size()-1){
                kimenet.append(this.szineszek.get(i).getSzineszNev()).append(", ");
            }else{
                kimenet.append(this.szineszek.get(i).getSzineszNev());
            }
        }
        return kimenet.toString();
    }
}
