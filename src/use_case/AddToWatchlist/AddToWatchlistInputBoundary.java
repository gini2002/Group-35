package use_case.AddToWatchlist;

import entity.Movie;

public interface AddToWatchlistInputBoundary {
    void execute(AddToWatchlistInputData addToWatchlistInputData);
}
