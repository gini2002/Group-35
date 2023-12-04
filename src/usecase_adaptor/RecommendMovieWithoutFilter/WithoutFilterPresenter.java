package usecase_adaptor.RecommendMovieWithoutFilter;

import use_case.RecommendMovieWithoutFilter.WithoutFilterOutputBoundary;
import use_case.RecommendMovieWithoutFilter.WithoutFilterOutputData;
import usecase_adaptor.ViewManagerModel;

/**
 * Presenter for the 'Recommend Movie Without Filter' use case.
 * This class handles the presentation logic by updating the view models
 * based on the outcome (success or failure) of the use case.
 * It implements the WithoutFilterOutputBoundary interface.
 */

public class WithoutFilterPresenter implements WithoutFilterOutputBoundary {
    private final WithoutFilterViewModel withoutFilterViewModel;

    private final WithoutFilterResultViewModel withoutFilterResultViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a WithoutFilterPresenter.
     *
     * @param viewManagerModel The model managing different views in the application.
     * @param withoutFilterViewModel ViewModel for displaying movies without filters.
     * @param withoutFilterResultViewModel ViewModel for managing the result view of the without filter operation.
     */

    public WithoutFilterPresenter(ViewManagerModel viewManagerModel, WithoutFilterViewModel withoutFilterViewModel, WithoutFilterResultViewModel withoutFilterResultViewModel) {
        this.withoutFilterViewModel = withoutFilterViewModel;
        this.viewManagerModel = viewManagerModel;
        this.withoutFilterResultViewModel = withoutFilterResultViewModel;

    }

    /**
     * Handles the successful scenario of the 'Without Filter' use case.
     * Updates the necessary view models with the data from the successful operation
     * and sets the active view accordingly.
     *
     * @param outputData The output data from the without filter operation, containing the recommended movies.
     */

    @Override
    public void WithoutFilterSuccessView(WithoutFilterOutputData outputData) {
        withoutFilterViewModel.setWithoutFilterMovies(outputData.getWithoutFilterMovies());
        withoutFilterViewModel.firePropertyChanged();
        withoutFilterResultViewModel.setWithoutFilterMovies(outputData.getWithoutFilterMovies());
        withoutFilterResultViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(withoutFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Handles the failure scenario of the 'Without Filter' use case.
     * Updates the view model with an error message indicating why the operation failed.
     *
     * @param error The error message describing the failure.
     */

    @Override
    public void WithoutFilterFailView(String error) {
        withoutFilterViewModel.setError(error);
        withoutFilterViewModel.firePropertyChanged();

    }

}