package use_case.AddToWatchlist;

import entity.Movie;

public class AddToWatchlistOutputData {
    private final String movieName;

    /**
     * initiate the object.
     * @param movie movie that is added to user's watchlist.
     */
    public AddToWatchlistOutputData(Movie movie) {
        this.movieName = movie.getName();
    }

    /**
     *
     * @return the string of successful message of adding movie to watchlist.
     */
    public String getMessage() {
        return "add " + movieName + " successfully";
    }

    /**
     *
     * @return the string of movie name that is added.
     */
    public String getMovieName() { return this.movieName; }
}
