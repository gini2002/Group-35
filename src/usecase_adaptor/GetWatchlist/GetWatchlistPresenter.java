package usecase_adaptor.GetWatchlist;

import use_case.GetWatchList.GetWatchListOutputBoundary;
import use_case.GetWatchList.GetWatchListOutputData;
import usecase_adaptor.ViewManagerModel;

public class GetWatchlistPresenter implements GetWatchListOutputBoundary {
    private final GetWatchListViewmodel getWatchListViewmodel;

    private ViewManagerModel viewManagerModel;

    public GetWatchlistPresenter(GetWatchListViewmodel getWatchListViewmodel,
                                 ViewManagerModel viewManagerModel) {
        this.getWatchListViewmodel = getWatchListViewmodel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void getWatchlistSuccessView(GetWatchListOutputData getWatchListOutputData) {
        this.viewManagerModel.setActiveView(getWatchListViewmodel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void getWatchlistFailView(String error) {

    }
}
