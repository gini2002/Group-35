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
 * Factory class for creating instances related to the Without Filter use case.
 * This class is responsible for setting up and initializing all necessary components
 * required for the Without Filter functionality, ensuring proper dependency management.
 */
public class WithoutFilterUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private WithoutFilterUseCaseFactory() {}

    /**
     * Creates and returns an instance of WithoutFilterView.
     * This method sets up the necessary controllers and view models for the Without Filter use case.
     *
     * @param viewManagerModel The model responsible for managing different views in the application.
     * @param viewModel The ViewModel for the Without Filter feature.
     * @param resultViewModel The ViewModel for displaying the results of the Without Filter feature.
     * @param movieDataAccessObject Data access object for movie-related operations.
     * @param withoutFilterDAO Data access object specifically for the Without Filter feature.
     * @return An instance of WithoutFilterView configured with the necessary components.
     */

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

    /**
     * Private helper method to create a WithoutFilterController.
     * This method encapsulates the construction logic for the controller used in the Without Filter feature,
     * including setting up the interactor and output boundary.
     *
     * @param viewManagerModel The model for managing views.
     * @param viewModel ViewModel for the Without Filter feature.
     * @param resultViewModel ViewModel for displaying the results of the Without Filter feature.
     * @param movieDataAccessObject DAO for movie-related operations.
     * @param withoutFilterDAO DAO specifically for Without Filter feature.
     * @return A new instance of WithoutFilterController.
     * @throws IOException if there is an IO error during the creation process.
     */

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
