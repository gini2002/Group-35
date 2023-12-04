package usecase_adaptor.DeleteWatchlist;

import use_case.DeleteWatchlist.DeleteWatchlistOutputBoundary;
import use_case.DeleteWatchlist.DeleteWatchlistOutputData;

/**
 * This class serves as the presenter for the delete watchlist use case.
 * It is responsible for preparing the view model for the user interface,
 * depending on the success or failure of the delete operation.
 * It implements the DeleteWatchlistOutputBoundary interface.
 */

public class DeleteWatchlistPresenter implements DeleteWatchlistOutputBoundary {

    private final DeleteWatchlistViewModel viewModel;

    /**
     * Constructs a new DeleteWatchlistPresenter with a specified view model.
     * This presenter uses the view model to communicate the results of
     * the delete watchlist operation to the user interface.
     *
     * @param viewModel The view model that will be updated based on the operation's result.
     */
    public DeleteWatchlistPresenter(DeleteWatchlistViewModel viewModel) {

        this.viewModel = viewModel;
    }

    /**
     * Prepares the view model for the failure view in case the delete operation fails.
     * This method updates the view model with an error message that can be displayed
     * to the user.
     *
     * @param error The error message to be displayed when the delete operation fails.
     */
    @Override
    public void PrepareFailView(String error) {
        DeleteWatchlistState state = viewModel.getState();
        state.setMovieExistError(error);
        viewModel.firePropertyChanged();

    }

    /**
     * Prepares the view model for the success view after a successful delete operation.
     * This method updates the view model with the appropriate success message and
     * clears any existing error messages.
     *
     * @param outputData The data related to the delete operation, including a success message.
     */
    @Override
    public void PrepareSuccessView(DeleteWatchlistOutputData outputData) {
        DeleteWatchlistState state = viewModel.getState();
        state.setMessage(outputData.getMessage());
        state.setMovieExistError(null);
        viewModel.firePropertyChanged();

    }
}