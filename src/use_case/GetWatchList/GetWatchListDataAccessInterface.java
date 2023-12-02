package use_case.GetWatchList;

import entity.Movie;
import entity.User;

import java.util.List;

public interface GetWatchListDataAccessInterface {
    List<Movie> getWatchlistMovies(String name);

    //void add_to_watchlist(User user, int movie_id);
}
