package hu.filmest.filmest.classes;

public class Felhasznalo {
    private int id;
    private String email;
    private int permission;

    public Felhasznalo(int id, String email,int permission) {
        this.id = id;
        this.email = email;
        this.permission = permission;
    }

    public int isAdmin() {
        return permission;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
