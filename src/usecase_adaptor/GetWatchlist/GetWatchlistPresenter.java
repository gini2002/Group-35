package usecase_adaptor.GetWatchlist;

import use_case.GetWatchList.GetWatchListOutputBoundary;
import use_case.GetWatchList.GetWatchListOutputData;
import usecase_adaptor.ViewManagerModel;

public class GetWatchlistPresenter implements GetWatchListOutputBoundary {
    private final GetWatchListViewmodel getWatchListViewmodel;

    private ViewManagerModel viewManagerModel;

    public GetWatchlistPresenter(GetWatchListViewmodel getWatchListViewmodel,
                                 ViewManagerModel viewManagerModel
                                 ) {
        this.getWatchListViewmodel = getWatchListViewmodel;
        this.viewManagerModel = viewManagerModel;
    }

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

    @Override
    public void getWatchlistFailView(String error) {
        GetWatchListState getWatchListState = getWatchListViewmodel.getState();
        getWatchListState.setGetWatchListError(error);
        getWatchListViewmodel.firePropertyChanged();
    }
}
