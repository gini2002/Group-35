package use_case.AddToWatchlist;

import entity.Movie;
import entity.User;

public class AddToWatchlistInputData {
    private final Movie movie;
    private final String userName;

    public  AddToWatchlistInputData(Movie movie, String userName) {

        this.movie = movie;
        this.userName = userName;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getUserName() {return userName;}
}
