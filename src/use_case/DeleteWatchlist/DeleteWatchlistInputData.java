package use_case.DeleteWatchlist;

import entity.Movie;

/**
 * Represents the input data for the delete watchlist use case.
 * This class encapsulates the data required to delete a movie from a user's watchlist,
 * including the specific movie and the username of the user.
 */

public class DeleteWatchlistInputData {
    private final Movie movie;
    private final String username;

    /**
     * Constructs a new DeleteWatchlistInputData object.
     *
     * @param movie The Movie object to be deleted from the watchlist.
     * @param username The username of the user whose watchlist will be modified.
     */

    public DeleteWatchlistInputData(Movie movie, String username) {

        this.movie = movie;
        this.username = username;
    }

    /**
     * Gets the Movie object.
     *
     * @return The Movie object to be deleted from the watchlist.
     */

    public Movie getMovie() {
        return movie;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user whose watchlist is being modified.
     */

    public String getUserName() {return username;}
}
