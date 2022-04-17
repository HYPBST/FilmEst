package hu.filmest.filmest.pivot;

public class FilmRendezoi {
    private int filmId;
    private int rendezoId;

    public FilmRendezoi(int filmId, int rendezoId) {
        this.filmId = filmId;
        this.rendezoId = rendezoId;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getRendezoId() {
        return rendezoId;
    }
}
