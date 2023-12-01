package use_case.DeleteWatchlist;
import entity.Movie;

public class DeleteWatchlistOutputData {

    private final String movieName;

    public DeleteWatchlistOutputData(Movie movie) {
        this.movieName = movie.getName();
    }

    public String getMessage() {
        return "delete " + movieName + " successfully";
    }

    /**
     *
     * @return the string of movie name that is added.
     */
    public String getMovieName() { return this.movieName; }
}

