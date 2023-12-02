package app;

import data_access.MovieDataAccessObject;
import data_access.WithoutFilterDAO;
import use_case.RecommendMovieWithoutFilter.WithoutFilterInputBoundary;
import usecase_adaptor.RecommendMovieWithoutFilter.*;
import usecase_adaptor.ViewManagerModel;
import use_case.RecommendMovieWithoutFilter.WithoutFilterInteractor;
import use_case.RecommendMovieWithoutFilter.WithoutFilterOutputBoundary;
// Other necessary imports...

import view.WithoutFilterView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for creating instances related to without filter use case.
 */
public class WithoutFilterUseCaseFactory {
    private WithoutFilterUseCaseFactory() {}

//    /**
//     * Creates a WithoutFilterView instance based on the provided parameters.
//     *
//     * @param viewManagerModel       The ViewManagerModel to be used.
//     * @param viewModel              The WithoutFilterViewModel to be used.
//     * @param resultViewModel        The WithoutFilterResultViewModel to be used.
//     * @param userDataAccessObject   The WithoutFilterDataAccessInterface for data access.
//     * @return The created WithoutFilterView instance.
//     */
    public static WithoutFilterView create(
            ViewManagerModel viewManagerModel,
            WithoutFilterViewModel viewModel,
            WithoutFilterResultViewModel resultViewModel,
            MovieDataAccessObject movieDataAccessObject, // Changed from WithoutFilterDataAccessInterface to MovieDataAccessObject
            WithoutFilterDAO withoutFilterDAO
            ) {
        try {
            WithoutFilterController controller = createWithoutFilterUseCase(viewManagerModel,
                    viewModel,
                    resultViewModel,
                    movieDataAccessObject,
                    withoutFilterDAO
                    );
            return new WithoutFilterView(viewModel, controller, viewManagerModel);
        } catch (IOException e) {
            // Handle the exception more gracefully (log it, show a user-friendly message, etc.).
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred.");
        }

        return null;
    }

//    /**
//     * Creates a WithoutFilterController instance for the without filter use case.
//     *
//     * @param viewManagerModel       The ViewManagerModel to be used.
//     * @param viewModel              The WithoutFilterViewModel to be used.
//     * @param resultViewModel        The WithoutFilterResultViewModel to be used.
//     * @param userDataAccessObject   The WithoutFilterDataAccessInterface for data access.
//     * @return The created WithoutFilterController instance.
//     * @throws IOException If an I/O error occurs during the creation.
//     */
    private static WithoutFilterController createWithoutFilterUseCase(
            ViewManagerModel viewManagerModel,
            WithoutFilterViewModel viewModel,
            WithoutFilterResultViewModel resultViewModel,
            MovieDataAccessObject movieDataAccessObject,
            WithoutFilterDAO withoutFilterDAO
            ) throws IOException {
        WithoutFilterOutputBoundary withoutFilterPresenter = new WithoutFilterPresenter(viewManagerModel, viewModel, resultViewModel);

        WithoutFilterInputBoundary withoutFilterInteractor = new WithoutFilterInteractor(
                withoutFilterPresenter,
                withoutFilterDAO,
                movieDataAccessObject);

        return new WithoutFilterController(withoutFilterInteractor);

    }
}
