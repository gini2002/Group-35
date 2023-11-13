package use_case.GetWatchList;

import entity.Movie;

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
        List<Movie> watchlist = watchListDataObject.getWatchlistMovies();//need to decide get based on id or name
        GetWatchListOutputData getWatchListOutputData = new GetWatchListOutputData(watchlist);
        getWatchListPresenter.getWatchlistSuccessView(getWatchListOutputData);
    }
}
