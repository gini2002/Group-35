package usecase_adaptor.AddToWatchlist;

import use_case.AddToWatchlist.AddToWatchlistInputBoundary;
import entity.Movie;
import entity.User;
import use_case.AddToWatchlist.AddToWatchlistInputData;

public class AddToWatchlistController {
    private final AddToWatchlistInputBoundary addToWatchlistInteractor;


    public AddToWatchlistController(AddToWatchlistInputBoundary addToWatchlistInputBoundary) {
        this.addToWatchlistInteractor = addToWatchlistInputBoundary;
    }

    public void execute (Movie movie, String userName) {
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(movie, userName);
        addToWatchlistInteractor.execute(inputData);
    }
}
