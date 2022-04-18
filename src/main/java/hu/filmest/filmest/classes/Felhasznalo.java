package hu.filmest.filmest.classes;

public class Felhasznalo {
    private int id;
    private String email;
    private String password;
    private int permission;

    public Felhasznalo(int id, String email,String password,int permission) {
        this.id = id;
        this.email = email;
        this.password=password;
        this.permission = permission;
    }

    public int isAdmin() {
        return permission;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
