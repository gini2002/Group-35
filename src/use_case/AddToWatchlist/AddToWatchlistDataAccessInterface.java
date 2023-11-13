package use_case.AddToWatchlist;

import entity.User;
import entity.Movie;

public interface AddToWatchlistDataAccessInterface {
    void saveMovie(String userName, Movie movie);

    User getUser(String userName);
}
