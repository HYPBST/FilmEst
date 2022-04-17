package hu.filmest.filmest.classes;

public class Rendezo {
    private int id;
    private String rendezoNev;

    public Rendezo(int id, String rendezoNev) {
        this.id = id;
        this.rendezoNev = rendezoNev;
    }

    public Rendezo(String rendezoNev) {
        this.rendezoNev = rendezoNev;
    }

    public int getId() {
        return id;
    }

    public String getRendezoNev() {
        return rendezoNev;
    }

    public void setRendezoNev(String rendezoNev) {
        this.rendezoNev = rendezoNev;
    }
}
