package hu.filmest.filmest.pivot;

public class FilmSzineszei {
    private int filmId;
    private int szineszId;

    public FilmSzineszei(int filmId, int szineszId) {
        this.filmId = filmId;
        this.szineszId = szineszId;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getSzineszId() {
        return szineszId;
    }
}
