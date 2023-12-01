package usecase_adaptor.AddToWatchlist;

import use_case.AddToWatchlist.AddToWatchlistOutputBoundary;
import use_case.AddToWatchlist.AddToWatchlistOutputData;

public class AddToWatchlistPresenter implements AddToWatchlistOutputBoundary {

    private final AddToWatchlistViewModel viewModel;

    /**
     * initiate a presenter.
     * @param viewModel a view model object.
     */
    public AddToWatchlistPresenter(AddToWatchlistViewModel viewModel) {

        this.viewModel = viewModel;
    }

    /**
     * prepare view if failed to add.
     * @param error string of error message when add failed.
     */
    @Override
    public void PrepareFailView(String error) {
        AddToWatchlistState state = viewModel.getState();
        state.setMovieExistError(error);
        viewModel.firePropertyChanged();

    }

    /**
     * prepare view if success added.
     * @param outputData that involve the movie being added.
     */
    @Override
    public void PrepareSuccessView(AddToWatchlistOutputData outputData) {
        AddToWatchlistState state = viewModel.getState();
        state.setMessage(outputData.getMessage());
        viewModel.firePropertyChanged();

    }
}
