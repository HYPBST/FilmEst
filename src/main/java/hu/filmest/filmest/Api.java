package hu.filmest.filmest;

import hu.filmest.filmest.classes.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Api {
    private static final String BASE_URL = "http://127.0.0.1:8000/";
    private static final String FILM_API_URL = BASE_URL+"api/filmek";
    private static final String KATEGORIA_API_URL = BASE_URL+"api/kategoriak";
    private static final String SZINESZ_API_URL = BASE_URL+"api/szineszek";
    private static final String RENDEZO_API_URL = BASE_URL+"api/rendezok";
    private static final String FILMKATEGORIA_API_URL = BASE_URL+"api/filmkategoria";
    private static final String FILMRENDEZO_API_URL = BASE_URL+"api/filmrendezo";
    private static final String FILMSZINESZ_API_URL = BASE_URL+"api/filmszinesz";
    private static final String FELHASZNALO_API_URL = BASE_URL+"api/felhasznalok";

    public static List<Film> getFilmList() throws IOException {
        Response response = RequestHandler.get(FILM_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Film>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }
    public static Film filmHozzaadasa(Film ujFilm) throws IOException {
        Gson jsonConvert = new Gson();
        String filmJson = jsonConvert.toJson(ujFilm);
        System.out.println(filmJson);
        Response response = RequestHandler.post(FILM_API_URL, filmJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Film.class);
    }
    public static Film filmModositasa(Film modositando) throws IOException {
        Gson jsonConvert = new Gson();
        String filmJson = jsonConvert.toJson(modositando);

        Response response = RequestHandler.put(FILM_API_URL+"/"+modositando.getId(), filmJson);

        String json = response.getContent();

        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Film.class);
    }
    public static boolean filmTorlese(int id) throws IOException {
        Response response = RequestHandler.delete(FILM_API_URL + "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
    public static List<Kategoria> getKategoriaList() throws IOException {
        Response response = RequestHandler.get(KATEGORIA_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Kategoria>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }
    public static Kategoria kategoriaHozzaadas(Kategoria ujKategoria) throws IOException {
        Gson jsonConvert = new Gson();
        String kategoriaJson = jsonConvert.toJson(ujKategoria);
        Response response = RequestHandler.post(KATEGORIA_API_URL, kategoriaJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Kategoria.class);
    }
    public static Kategoria kategoriaModositas(Kategoria modositando) throws IOException {
        Gson jsonConvert = new Gson();
        String kategoriaJson = jsonConvert.toJson(modositando);
        Response response = RequestHandler.put(KATEGORIA_API_URL+"/"+modositando.getId(), kategoriaJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Kategoria.class);
    }
    public static boolean kategoriaTorles(int id) throws IOException {
        Response response = RequestHandler.delete(KATEGORIA_API_URL + "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
    public static List<Rendezo> getRendezokList() throws IOException {
        Response response = RequestHandler.get(RENDEZO_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Rendezo>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }
    public static Rendezo rendezoHozzaadas(Rendezo ujRendezo) throws IOException {
        Gson jsonConvert = new Gson();
        String rendezoJson = jsonConvert.toJson(ujRendezo);
        Response response = RequestHandler.post(RENDEZO_API_URL, rendezoJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Rendezo.class);
    }
    public static Rendezo rendezoModositas(Rendezo modositando) throws IOException {
        Gson jsonConvert = new Gson();
        String rendezoJson = jsonConvert.toJson(modositando);
        Response response = RequestHandler.put(RENDEZO_API_URL+"/"+modositando.getId(), rendezoJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Rendezo.class);
    }
    public static boolean rendezoTorles(int id) throws IOException {
        Response response = RequestHandler.delete(RENDEZO_API_URL + "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
}
