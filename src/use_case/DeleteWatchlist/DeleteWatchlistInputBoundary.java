package use_case.DeleteWatchlist;

/**
 * Interface defining the input boundary for the delete watchlist use case.
 * This interface represents the contract for initiating the process of deleting
 * movies from a user's watchlist. It is typically implemented by a use case interactor.
 */

public interface DeleteWatchlistInputBoundary {

    /**
     * Executes the operation of deleting a movie from a user's watchlist.
     * This method takes an input data object containing the necessary information
     * to perform the deletion operation, such as the user's identification and the movie to delete.
     *
     * @param DeleteWatchlistInputData An instance of DeleteWatchlistInputData containing
     *                                 the details required for the delete operation.
     */

    void execute(DeleteWatchlistInputData DeleteWatchlistInputData);
}
