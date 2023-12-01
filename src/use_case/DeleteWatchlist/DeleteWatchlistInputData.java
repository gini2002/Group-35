package use_case.DeleteWatchlist;

import entity.Movie;

public class DeleteWatchlistInputData {
    private final Movie movie;
    private final String username;

    public DeleteWatchlistInputData(Movie movie, String username) {

        this.movie = movie;
        this.username = username;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getUserName() {return username;}
}
