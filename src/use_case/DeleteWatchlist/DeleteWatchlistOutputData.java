package use_case.DeleteWatchlist;
import entity.Movie;

/**
 * Represents the output data for the delete watchlist use case.
 * This class encapsulates the details of the movie that was successfully deleted
 * from a user's watchlist, primarily used for communicating the results back to the presentation layer.
 */

public class DeleteWatchlistOutputData {

    private final String movieName;

    /**
     * Constructs a new DeleteWatchlistOutputData object.
     *
     * @param movie The Movie object that has been deleted from the watchlist.
     *              The movie name is extracted and stored for later retrieval.
     */

    public DeleteWatchlistOutputData(Movie movie) {
        this.movieName = movie.getName();
    }

    /**
     * Gets a success message indicating the successful deletion of a movie.
     *
     * @return A string message that includes the name of the movie that was deleted.
     */

    public String getMessage() {
        return "delete " + movieName + " successfully";
    }

    /**
     * Retrieves the name of the movie that was deleted.
     *
     * @return The name of the deleted movie.
     */
    public String getMovieName() { return this.movieName; }
}

