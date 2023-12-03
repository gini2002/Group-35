package use_case.DeleteWatchlist;

/**
 * Interface defining the output boundary for the delete watchlist use case.
 * This interface represents the contract for presenting the results (success or failure)
 * of an attempt to delete a movie from a user's watchlist.
 */

public interface DeleteWatchlistOutputBoundary {

    /**
     * Prepares and presents a view for a failed deletion attempt.
     * This method is called when the deletion process encounters an error or when the specified
     * movie cannot be deleted from the watchlist.
     *
     * @param error A descriptive error message explaining why the deletion failed.
     */

    void PrepareFailView(String error);

    /**
     * Prepares and presents a view for a successful deletion operation.
     * This method is called when a movie is successfully deleted from the user's watchlist.
     *
     * @param outputData Data representing the successful outcome of the deletion,
     *                   typically including details about the movie that was deleted.
     */
    void PrepareSuccessView(DeleteWatchlistOutputData outputData);
}
