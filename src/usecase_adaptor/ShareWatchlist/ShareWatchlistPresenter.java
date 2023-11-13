package usecase_adaptor.ShareWatchlist;


import use_case.ShareWatchlist.ShareWatchlistOutputBoundary;
import use_case.ShareWatchlist.ShareWatchlistOutputData;


public class ShareWatchlistPresenter implements ShareWatchlistOutputBoundary {

    private final ShareWatchlistViewModel viewModel;

    public ShareWatchlistPresenter(ShareWatchlistViewModel viewModel) {
        this.viewModel = viewModel;
    }
    @Override
    public void prepareSuccessView(ShareWatchlistOutputData outputData) {
        ShareWatchlistState state = viewModel.getState();
        state.setReceiverName(outputData.getUserName());
        viewModel.firePropertyChanged();
        //TODO change view or add pop up window.
    }

    @Override
    public void prepareFailView(String error) {
        ShareWatchlistState state = viewModel.getState();
        state.setError(error);
        viewModel.firePropertyChanged();
    }
}
