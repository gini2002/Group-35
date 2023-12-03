package use_case.AddToWatchlist;

import entity.User;
import entity.Movie;

public interface AddToWatchlistDataAccessInterface {

    /**
     * save movie in user with useName's watchlist.
     * @param userName of user who want to add movie.
     * @param movie that is being added.
     */
    void saveMovie(String userName, Movie movie);

    /**
     *
     * @param userName of user who want to add movie.
     * @return the user obeject correspond to user name.
     */
    User getUser(String userName);

    /**
     *
     * @return the path of csv file.
     */
    String getPath();
}
