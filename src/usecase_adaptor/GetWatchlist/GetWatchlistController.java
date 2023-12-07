package usecase_adaptor.GetWatchlist;

import use_case.GetWatchList.GetWatchListInputBoundary;
import use_case.GetWatchList.GetWatchListInputData;
/**
 * The GetWatchlistController class serves as a controller for initiating the get watchlist for certain user use case.
 * It delegates the execution to the corresponding interactor.
 */
public class GetWatchlistController {
    /** The interactor responsible for executing the get watchlist for certain user use case. */
    final GetWatchListInputBoundary getWatchListInteractor;

    /**
     * Constructs a GetWatchlistController with the provided interactor.
     *
     * @param getWatchListInteractor The interactor for executing get watchlist for certain user use case.
     */
    public GetWatchlistController(GetWatchListInputBoundary getWatchListInteractor) {
        this.getWatchListInteractor = getWatchListInteractor;
    }
    /**
     * Initiates the execution of the get watchlist for a certain user use case with the username.
     *
     * @param name The name of the user that we are getting watchlist for.
     */
    public void execute(String name){
        GetWatchListInputData getWatchListInputData = new GetWatchListInputData(name);
        getWatchListInteractor.execute(getWatchListInputData);
    }
}
