package use_case.GetWatchList;

import data_access.GetWatchListDAO;
import entity.Movie;
import entity.User;

import java.util.List;

public interface GetWatchListDataAccessInterface {
    List<Movie> getWatchlistMovies(String name);

    GetWatchListDAO updatecsvpath(String path);

    String getpath();

    //void add_to_watchlist(User user, int movie_id);
}
