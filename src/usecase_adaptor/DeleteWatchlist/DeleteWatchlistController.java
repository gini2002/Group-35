package usecase_adaptor.DeleteWatchlist;

import entity.Movie;
import use_case.DeleteWatchlist.DeleteWatchlistInputBoundary;
import use_case.DeleteWatchlist.DeleteWatchlistInputData;

/**
 * This controller handles the deletion of movies from user's watchlist.
 * It serves as an intermediary between the user interface and the application logic
 * for deleting movies from the watchlist.
 */

public class DeleteWatchlistController {

    private final DeleteWatchlistInputBoundary deleteWatchlistInteractor;

    /**
     * Constructs a new DeleteWatchlistController with a specified DeleteWatchlistInputBoundary.
     * This constructor allows the controller to interact with the application logic
     * for deleting movies from the watchlist.
     *
     * @param deleteWatchlistInputBoundary the input boundary to interact with the delete watchlist use case.
     */
    public DeleteWatchlistController(DeleteWatchlistInputBoundary deleteWatchlistInputBoundary) {
        this.deleteWatchlistInteractor = deleteWatchlistInputBoundary;
    }

    /**
     * Executes the action of deleting a specified movie from a user's watchlist.
     * It takes a movie and the username of the user, creates an input data object,
     * and passes it to the delete watchlist interactor for processing.
     *
     * @param movie the movie to be deleted from the watchlist.
     * @param userName the username of the user who wants to delete the movie from their watchlist.
     */
    public void execute (Movie movie, String userName) {
        DeleteWatchlistInputData inputData = new DeleteWatchlistInputData(movie, userName);
        deleteWatchlistInteractor.execute(inputData);
    }
}


