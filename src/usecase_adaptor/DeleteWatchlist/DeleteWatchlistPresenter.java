package usecase_adaptor.AddToWatchlist;

import use_case.AddToWatchlist.AddToWatchlistOutputBoundary;
import use_case.AddToWatchlist.AddToWatchlistOutputData;

public class AddToWatchlistPresenter implements AddToWatchlistOutputBoundary {

    private final AddToWatchlistViewModel viewModel;

    public AddToWatchlistPresenter(AddToWatchlistViewModel viewModel) {

        this.viewModel = viewModel;
    }

    @Override
    public void PrepareFailView(String error) {
        AddToWatchlistState state = viewModel.getState();
        state.setMovieExistError(error);
        viewModel.firePropertyChanged();

    }

    @Override
    public void PrepareSuccessView(AddToWatchlistOutputData outputData) {
        AddToWatchlistState state = viewModel.getState();
        state.setMessage(outputData.getMessage());
        viewModel.firePropertyChanged();

    }
}
