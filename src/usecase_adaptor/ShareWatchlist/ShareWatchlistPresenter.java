package usecase_adaptor.ShareWatchlist;


import use_case.ShareWatchlist.ShareWatchlistOutputBoundary;
import use_case.ShareWatchlist.ShareWatchlistOutputData;


public class ShareWatchlistPresenter implements ShareWatchlistOutputBoundary {

    private final ShareWatchlistViewModel viewModel;

    /**
     * initiate the presenter.
     * @param viewModel a view model.
     */
    public ShareWatchlistPresenter(ShareWatchlistViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * prepare view after success shared.
     * @param outputData that involve the receiver's userName.
     */
    @Override
    public void prepareSuccessView(ShareWatchlistOutputData outputData) {
        ShareWatchlistState state = viewModel.getState();
        state.setReceiverName(outputData.getUserName());
        viewModel.firePropertyChanged();
    }

    /**
     * prepare view after failed shared.
     * @param error error message of fail view.
     */
    @Override
    public void prepareFailView(String error) {
        ShareWatchlistState state = viewModel.getState();
        state.setError(error);
        viewModel.firePropertyChanged();
    }
}
