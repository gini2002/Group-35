package use_case.AddToWatchlist;

import entity.Movie;
import entity.User;

public class AddToWatchlistInputData {
    private final Movie movie;
    private final String userName;

    /**
     *
     * @param movie movie object to be added.
     * @param userName string name of the user who want to add the movie.
     */

    public  AddToWatchlistInputData(Movie movie, String userName) {

        this.movie = movie;
        this.userName = userName;
    }

    /**
     *
     * @return movie want to be added
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     *
     * @return string username of user who want to add movie.
     */
    public String getUserName() {return userName;}
}
