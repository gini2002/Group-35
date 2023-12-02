package use_case.DeleteWatchlist;

import data_access.WatchlistDAO;
import entity.Movie;
import entity.User;

import java.util.List;

public interface DeleteWatchlistDataAccessInterface {

    public boolean removeMovieFromWatchlist(String username, int movieId);

    public List<Integer> getWatchlistMoviesID(String name);
//    Movie getMovie(Movie movie);

}
