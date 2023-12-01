package usecase_adaptor.DeleteWatchlist;

public class DeleteWatchlistState {
    private String message = "";
    private String movieExistError = null;

    /**
     * initiate a state.
     */
    public DeleteWatchlistState() {}

    /**
     * save message in state.
     * @param message string message that is shown on screen.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return the saved message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * save error message in state.
     * @param errorName String of error that is shown on screen.
     */
    public void setMovieExistError(String errorName) {
        this.movieExistError = errorName;
    }

    /**
     *
     * @return the error saved in the state.
     */
    public String getMovieExistError() {
        return movieExistError;
    }
}
