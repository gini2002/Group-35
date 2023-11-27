package use_case.AddToWatchlist;

import entity.Movie;

public class AddToWatchlistOutputData {
    private final String movieName;

    public AddToWatchlistOutputData(Movie movie) {
        this.movieName = movie.getName();
    }

    public String getMessage() {
        return "add " + movieName + " successfully";
    }

    public String getMovieName() { return this.movieName; }
}
