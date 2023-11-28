package app;

import use_case.MovieSearchByKeyword.RecommendInputBoundary;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.SearchList.SearchListViewModel;
import usecase_adaptor.ViewManagerModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameController;
import usecase_adaptor.MovieSearchByKeyword.SearchByNamePresenter;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import use_case.MovieSearchByKeyword.SearchByNameInteractor;
import use_case.MovieSearchByKeyword.SearchByNameOutputBoundary;
import use_case.MovieSearchByKeyword.SearchByNameDataAccessInterface;
//import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import view.MovieRecommendView;
import entity.UserFactory;
import entity.CommonUserFactory;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for creating instances related to movie search use case.
 */
public class MovieSearchUseCaseFactory {
    private MovieSearchUseCaseFactory() {}

    /**
     * Creates a MovieRecommendView instance based on the provided parameters.
     *
     * @param viewManagerModel      The ViewManagerModel to be used.
     * @param viewModel             The SearchByNameViewModel to be used.
     * @param resultViewModel       The MovieResultViewModel to be used.
     * @param searchListViewModel   The SearchListViewModel to be used.
     * @param userDataAccessObject  The SearchByNameDataAccessInterface for movie data access.
     * @return The created MovieRecommendView instance.
     */

    public static MovieRecommendView create(
            ViewManagerModel viewManagerModel,
            SearchByNameViewModel viewModel,
            MovieResultViewModel resultViewModel,
            SearchListViewModel searchListViewModel,
            SearchByNameDataAccessInterface userDataAccessObject) {
        try {
            SearchByNameController controller = createMovieSearchUseCase(viewManagerModel, viewModel, resultViewModel, searchListViewModel, userDataAccessObject);
            return new MovieRecommendView(viewModel, controller, viewManagerModel);
        } catch (IOException e) {
            // Handle the exception more gracefully (log it, show a user-friendly message, etc.).
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not open movie data file.");
        }

        return null;
    }

    /**
     * Creates a SearchByNameController instance for the movie search use case.
     *
     * @param viewManagerModel      The ViewManagerModel to be used.
     * @param viewModel             The SearchByNameViewModel to be used.
     * @param resultViewModel       The MovieResultViewModel to be used.
     * @param searchListViewModel   The SearchListViewModel to be used.
     * @param userDataAccessObject  The SearchByNameDataAccessInterface for movie data access.
     * @return The created SearchByNameController instance.
     * @throws IOException If an I/O error occurs during the creation.
     */
    private static SearchByNameController createMovieSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchByNameViewModel viewModel,
            MovieResultViewModel resultViewModel,
            SearchListViewModel searchListViewModel,
            SearchByNameDataAccessInterface userDataAccessObject) throws IOException {
        SearchByNameOutputBoundary searchByNameOutputBoundary = new SearchByNamePresenter(viewManagerModel, viewModel, resultViewModel, searchListViewModel);

        UserFactory userFactory = new CommonUserFactory();

        RecommendInputBoundary searchByNameInteractor = new SearchByNameInteractor(userDataAccessObject, searchByNameOutputBoundary);

        return new SearchByNameController(searchByNameInteractor);

    }


}