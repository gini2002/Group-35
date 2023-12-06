package use_case.GetWatchList;

import data_access.GetWatchListDAO;
import data_access.WatchlistDAO;
import entity.Movie;

import java.util.ArrayList;
import java.util.List;
/**
 * The GetWatchListInteractor class represents the interactor for get watchlist use case.
 * It implements the GetWatchListInputBoundary interface and is responsible for executing the use case logic.
 */
public class GetWatchListInteractor implements GetWatchListInputBoundary {
    /** The data access object get information we need. */
    final GetWatchListDataAccessInterface watchListDataObject;
    /** The presenter that show the result of the use case. */
    final GetWatchListOutputBoundary getWatchListPresenter;

    /**
     * Constructs a GetDetailMovieInteractor with the provided data access object and presenter.
     *
     * @param getWatchListPresenter The presenter that show the result of the use case.
     * @param  watchListDataObject The data access object get information we need.
     */
    public GetWatchListInteractor(GetWatchListDataAccessInterface watchListDataObject,
                                  GetWatchListOutputBoundary getWatchListPresenter) {
        this.watchListDataObject = watchListDataObject;
        this.getWatchListPresenter = getWatchListPresenter;
    }

    /**
     * Executes get watchlist use case based on the provided input data.
     * @param getWatchListIntputData The input data for the get watchlist use case.
     */
    @Override
    public void execute(GetWatchListInputData getWatchListIntputData) {
        String name = getWatchListIntputData.getName();
        // int id = getWatchListIntputData.getId();
        GetWatchListDAO watchListDataObject2 = watchListDataObject.updatecsvpath(watchListDataObject.getpath());
        List<Movie> watchlist = watchListDataObject2.getWatchlistMovies(name);
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
