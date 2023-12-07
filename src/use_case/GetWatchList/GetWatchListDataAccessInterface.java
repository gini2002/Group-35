package use_case.GetWatchList;

import data_access.GetWatchListDAO;
import entity.Movie;
import entity.User;

import java.util.List;
/**
 * The GetWatchListDataAccessInterface defines the data access to get watchlist use case.
 */
public interface GetWatchListDataAccessInterface {
    /**
     * Retrieves a list of movies that in the watchlist of the user with the input username.
     *
     * @param name The name of user that we are getting watchlist for/
     * @return A list of movies that in the watchlist of the user.
     */
    List<Movie> getWatchlistMovies(String name);

    /**
     * Retrieves the GetWatchListDAO that need to reload.
     * @param path the new file path to get information for.
     * @return The GetWatchListDAO that need to reload.
     */
    GetWatchListDAO updatecsvpath(String path);
    /**
     * Retrieves the file path that need to reload.
     * @return The file path that need to reload.
     */
    String getpath();

    //void add_to_watchlist(User user, int movie_id);
}
