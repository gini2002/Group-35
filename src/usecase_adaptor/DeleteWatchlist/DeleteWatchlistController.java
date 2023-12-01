package usecase_adaptor.DeleteWatchlist;

import entity.Movie;
import use_case.DeleteWatchlist.DeleteWatchlistInputBoundary;
import use_case.DeleteWatchlist.DeleteWatchlistInputData;

public class DeleteWatchlistController {

    private final DeleteWatchlistInputBoundary deleteWatchlistInteractor;

    /**
     * initiate a controller.
     * @param deleteWatchlistInputBoundary the input boundary.
     */
    public DeleteWatchlistController(DeleteWatchlistInputBoundary deleteWatchlistInputBoundary) {
        this.deleteWatchlistInteractor = deleteWatchlistInputBoundary;
    }

    /**
     * add movie to the watchlist.
     * @param movie that want to be added.
     * @param userName of user who want to save movie.
     */
    public void execute (Movie movie, String userName) {
        DeleteWatchlistInputData inputData = new DeleteWatchlistInputData(movie, userName);
        deleteWatchlistInteractor.execute(inputData);
    }
}


