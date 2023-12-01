package usecase_adaptor.AddToWatchlist;

public class AddToWatchlistState {
    private String message = "";
    private String movieExistError = null;

    public AddToWatchlistState() {}

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMovieExistError(String errorName) {
        this.movieExistError = errorName;
    }

    public String getMovieExistError() {
        return movieExistError;
    }

}
