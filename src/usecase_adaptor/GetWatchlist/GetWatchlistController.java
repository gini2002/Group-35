package usecase_adaptor.GetWatchlist;

import use_case.GetWatchList.GetWatchListInputBoundary;
import use_case.GetWatchList.GetWatchListInputData;

public class GetWatchlistController {
    final GetWatchListInputBoundary getWatchListInteractor;
    public GetWatchlistController(GetWatchListInputBoundary getWatchListInteractor) {
        this.getWatchListInteractor = getWatchListInteractor;
    }

    public void excute(String name, int id){
        GetWatchListInputData getWatchListInputData = new GetWatchListInputData(name, id);
        getWatchListInteractor.execute(getWatchListInputData);
    }
}
