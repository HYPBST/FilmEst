package hu.filmest.filmest;

public class ApiError {
    String message;

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
