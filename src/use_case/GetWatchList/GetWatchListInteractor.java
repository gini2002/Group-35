package use_case.GetWatchList;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class GetWatchListInteractor implements GetWatchListInputBoundary {
    final GetWatchListDataAccessInterface watchListDataObject;
    final GetWatchListOutputBoundary getWatchListPresenter;

    public GetWatchListInteractor(GetWatchListDataAccessInterface watchListDataObject,
                                  GetWatchListOutputBoundary getWatchListPresenter) {
        this.watchListDataObject = watchListDataObject;
        this.getWatchListPresenter = getWatchListPresenter;
    }

    @Override
    public void execute(GetWatchListInputData getWatchListIntputData) {
        String name = getWatchListIntputData.getName();
        // int id = getWatchListIntputData.getId();
        List<Movie> watchlist = watchListDataObject.getWatchlistMovies(name);
        List<String> names = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        List<String> poster_url = new ArrayList<>();
        for (Movie movie : watchlist) {
            names.add(movie.getName());
            poster_url.add(movie.getPoster_path());
            ids.add(movie.getID());
        }
        GetWatchListOutputData getWatchListOutputData = new GetWatchListOutputData(names, poster_url, ids, name);
        getWatchListPresenter.getWatchlistSuccessView(getWatchListOutputData);
    }
}
