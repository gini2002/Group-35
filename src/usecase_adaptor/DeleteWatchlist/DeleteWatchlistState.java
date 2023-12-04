package usecase_adaptor.DeleteWatchlist;

/**
 * This class represents the state for the delete watchlist operation.
 * It holds the messages and errors related to the operation which can be used
 * to update the user interface accordingly. It manages the state of the
 * delete watchlist process, including both success and error messages.
 */

public class DeleteWatchlistState {
    private String message = "";
    private String movieExistError = null;

    /**
     * Constructs a new DeleteWatchlistState.
     * This constructor initializes the state with default values for message
     * and movieExistError.
     */
    public DeleteWatchlistState() {}

    /**
     * Sets the message in the state. This message is typically used to display
     * success notifications related to the delete watchlist operation on the screen.
     *
     * @param message The message that is to be shown on the screen.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the message saved in the state.
     *
     * @return The current success message stored in the state.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message in the state. This error message is used to
     * display error notifications related to the delete watchlist operation on the screen.
     *
     * @param errorName The error message that is to be shown on the screen.
     */
    public void setMovieExistError(String errorName) {
        this.movieExistError = errorName;
    }

    /**
     * Retrieves the error message saved in the state.
     *
     * @return The current error message stored in the state, if any.
     */
    public String getMovieExistError() {
        return movieExistError;
    }
}
