package use_case.AddToWatchlist;

import entity.Movie;

public interface AddToWatchlistInputBoundary {
    /**
     *
     * @param addToWatchlistInputData include movie that want to be saved.
     */
    void execute(AddToWatchlistInputData addToWatchlistInputData);
}
