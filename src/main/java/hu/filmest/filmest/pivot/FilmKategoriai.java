package hu.filmest.filmest.pivot;

public class FilmKategoriai {
    private int filmId;
    private int kategoriaId;

    public FilmKategoriai(int filmId, int kategoriaId) {
        this.filmId = filmId;
        this.kategoriaId = kategoriaId;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getKategoriaId() {
        return kategoriaId;
    }
}
