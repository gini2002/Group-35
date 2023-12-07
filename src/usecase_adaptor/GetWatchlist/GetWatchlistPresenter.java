package usecase_adaptor.GetWatchlist;

import use_case.GetWatchList.GetWatchListOutputBoundary;
import use_case.GetWatchList.GetWatchListOutputData;
import usecase_adaptor.ViewManagerModel;
/**
 * The GetWatchlistPresenter class acts as a presenter for get the watchlist for a user with certain username use case.
 * It prepares the view models based on the output data and updates the view manager accordingly.
 */
public class GetWatchlistPresenter implements GetWatchListOutputBoundary {
    /** The view model for get the watchlist for a user with certain username. */
    private final GetWatchListViewmodel getWatchListViewmodel;
    /** The view manager model for controlling the active view. */
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a GetDetailMoviePresenter with the provided view models and view manager model.
     * @param viewManagerModel The view manager model for controlling the active view.
     * @param getWatchListViewmodel The view model for get the watchlist for a user with certain username.
     */
    public GetWatchlistPresenter(GetWatchListViewmodel getWatchListViewmodel,
                                 ViewManagerModel viewManagerModel
                                 ) {
        this.getWatchListViewmodel = getWatchListViewmodel;
        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Prepares the success view based on the provided output data.
     * @param getWatchListOutputData The output data containing information for the success view.
     */
    @Override
    public void getWatchlistSuccessView(GetWatchListOutputData getWatchListOutputData) {
        GetWatchListState getWatchListState = getWatchListViewmodel.getState();
        getWatchListState.setWatchlistNames(getWatchListOutputData.getNames());
        getWatchListState.setWatchlistPosters(getWatchListOutputData.getPoster_urls());
        getWatchListState.setLoggedinusername(getWatchListOutputData.getLogged_in_username());
        getWatchListState.setIds(getWatchListOutputData.getIds());
        getWatchListViewmodel.setWatchlist(getWatchListOutputData.getNames(),
                getWatchListOutputData.getPoster_urls(), getWatchListOutputData.getIds());
        getWatchListViewmodel.setLogged_in_username(getWatchListOutputData.getLogged_in_username());
        getWatchListViewmodel.setState(getWatchListState);
        // getWatchListViewmodel.firePropertyChanged();

        viewManagerModel.setActiveView(getWatchListViewmodel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view based on the provided output data.
     * @param error The error message shown in the fail view.
     */
    @Override
    public void getWatchlistFailView(String error) {
        GetWatchListState getWatchListState = getWatchListViewmodel.getState();
        getWatchListState.setGetWatchListError(error);
        getWatchListViewmodel.firePropertyChanged();
    }
}
