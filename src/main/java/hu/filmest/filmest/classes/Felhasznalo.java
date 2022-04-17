package hu.filmest.filmest.classes;

public class Felhasznalo {
    private int id;
    private String email;

    public Felhasznalo(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
