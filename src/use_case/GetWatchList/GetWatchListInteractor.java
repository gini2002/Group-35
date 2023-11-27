package use_case.GetWatchList;

import entity.Movie;
import entity.Watchlist;

import java.awt.event.ActionEvent;
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
        int id = getWatchListIntputData.getId();
        List<Movie> watchlist = watchListDataObject.getWatchlistMovies();
        List<String> names = new ArrayList<>();
        for (Movie movie : watchlist) {
            names.add(movie.getName());
        }
        List<String> poster_url = new ArrayList<>();
        for (Movie movie : watchlist) {
            poster_url.add(movie.getPoster_path());
        }
        //TODO: need to decide get based on id or name
        GetWatchListOutputData getWatchListOutputData = new GetWatchListOutputData(names, poster_url);
        getWatchListPresenter.getWatchlistSuccessView(getWatchListOutputData);
    }
}
