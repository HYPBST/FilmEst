package hu.filmest.filmest.classes;

public class Kategoria {
    private int id;
    private String kategoria;

    public Kategoria(int id, String kategoria) {
        this.id = id;
        this.kategoria = kategoria;
    }

    public Kategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public int getId() {
        return id;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
}
