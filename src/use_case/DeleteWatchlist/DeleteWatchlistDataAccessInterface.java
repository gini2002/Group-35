package use_case.DeleteWatchlist;

import data_access.WatchlistDAO;
import entity.Movie;
import entity.User;

import java.util.List;

/**
 * Interface defining the data access contract for deleting movies from a user's watchlist.
 * This interface is implemented by classes that interact with the data layer to perform
 * operations related to managing watchlists, specifically for the deletion of movies.
 */

public interface DeleteWatchlistDataAccessInterface {

    /**
     * Retrieves a User object based on the username.
     *
     * @param userName The username of the user whose details are to be fetched.
     * @return The User object corresponding to the given username.
     */

    User getUser(String userName);

    /**
     * Saves a movie to a user's watchlist after deletion operation.
     * This method is typically used to update the user's watchlist in the data store
     * after a movie has been deleted.
     *
     * @param userName The username of the user.
     * @param movie The Movie object that has been deleted from the watchlist.
     */

    void saveMovie(String userName, Movie movie);

    /**
     * Gets the file path of the data storage used for the watchlist.
     *
     * @return The file path as a String.
     */

    String getPath();
}

