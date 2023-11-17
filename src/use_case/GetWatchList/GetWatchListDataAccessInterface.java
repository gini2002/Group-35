package use_case.GetWatchList;

import entity.Movie;

import java.util.List;

public interface GetWatchListDataAccessInterface {
    List<Movie> getWatchlistMovies();
}
