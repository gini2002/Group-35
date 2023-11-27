package usecase_adaptor.GetWatchlist;

import entity.Movie;
import use_case.GetWatchList.GetWatchListDataAccessInterface;

import java.util.List;

public class GetWatchlistDAO implements GetWatchListDataAccessInterface {

    @Override
    public List<Movie> getWatchlistMovies() {
        return null;
    }
}
