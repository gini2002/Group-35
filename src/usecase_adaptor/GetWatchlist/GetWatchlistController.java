package usecase_adaptor.GetWatchlist;

import use_case.GetWatchList.GetWatchListInputBoundary;
import use_case.GetWatchList.GetWatchListInputData;

public class GetWatchlistController {
    final GetWatchListInputBoundary getWatchListInteractor;
    public GetWatchlistController(GetWatchListInputBoundary getWatchListInteractor) {
        this.getWatchListInteractor = getWatchListInteractor;
    }

    public void execute(String name){
        GetWatchListInputData getWatchListInputData = new GetWatchListInputData(name);
        getWatchListInteractor.execute(getWatchListInputData);
    }
}
