package usecase_adaptor.AddToWatchlist;

import use_case.AddToWatchlist.AddToWatchlistInputBoundary;
import entity.Movie;
import entity.User;
import use_case.AddToWatchlist.AddToWatchlistInputData;

public class AddToWatchlistController {
    private final AddToWatchlistInputBoundary addToWatchlistInteractor;

    /**
     * initiate a controller.
     * @param addToWatchlistInputBoundary the input boundary.
     */
    public AddToWatchlistController(AddToWatchlistInputBoundary addToWatchlistInputBoundary) {
        this.addToWatchlistInteractor = addToWatchlistInputBoundary;
    }

    /**
     * add movie to the watchlist.
     * @param movie that want to be added.
     * @param userName of user who want to save movie.
     */
    public void execute (Movie movie, String userName) {
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(movie, userName);
        addToWatchlistInteractor.execute(inputData);
    }
}
