package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import use_case.GetDetailMovie.GetDetailMovieDataAccessInterface;
import use_case.GetDetailMovie.GetDetailMovieInputBoundary;
import use_case.GetDetailMovie.GetDetailMovieInteractor;
import use_case.GetDetailMovie.GetDetailMovieOutputBoundary;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.GetDetailOfMovie.GetDetailMoviePresenter;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterResultViewModel;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;
import usecase_adaptor.ViewManagerModel;
import view.WithoutFilterResultView;

import java.io.IOException;

/**
 * Factory class for creating instances related to the 'Without Filter Result' use case.
 * This class serves as a central point for constructing the necessary components
 * for the use case, ensuring that dependencies are correctly instantiated and injected.
 */

public class WithoutFilterResultUseCaseFactory {

    private WithoutFilterResultUseCaseFactory() {}

    /**
     * Creates a WithoutFilterResultView object with the necessary dependencies.
     *
     * @param viewManagerModel The view manager model for managing views.
     * @param withoutFilterViewModel ViewModel for the without filter feature.
     * @param withoutFilterResultViewModel ViewModel for the without filter result.
     * @param getDetailMovieViewModel ViewModel for movie detail retrieval.
     * @param detailMovieDataAccessInterface Data access interface for movie details.
     * @return A new instance of WithoutFilterResultView.
     * @throws Exception if there is an error during the creation process.
     */

    public static WithoutFilterResultView create(
            ViewManagerModel viewManagerModel,
            WithoutFilterViewModel withoutFilterViewModel,
            WithoutFilterResultViewModel withoutFilterResultViewModel,
            GetDetailMovieViewModel getDetailMovieViewModel,
            GetDetailMovieDataAccessInterface detailMovieDataAccessInterface) {
        try {
            GetDetailMovieController getDetailMovieController = createMovieResultUseCase(viewManagerModel, withoutFilterViewModel, withoutFilterResultViewModel, getDetailMovieViewModel, detailMovieDataAccessInterface);
            return new WithoutFilterResultView(withoutFilterResultViewModel, withoutFilterViewModel, viewManagerModel, getDetailMovieController);
        } catch (Exception e) {
            // Handle the exception (log it, show a user-friendly message, etc.)
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Private helper method to create a GetDetailMovieController.
     * This method encapsulates the construction logic for the controller used in the movie detail retrieval feature.
     *
     * @param viewManagerModel The view manager model.
     * @param withoutFilterViewModel ViewModel for without filter feature.
     * @param withoutFilterResultViewModel ViewModel for the result of the without filter feature.
     * @param getDetailMovieViewModel ViewModel for getting movie details.
     * @param detailMovieDataAccessInterface Interface for movie detail data access.
     * @return A new instance of GetDetailMovieController.
     * @throws IOException if there is an IO error during the creation process.
     */

    private static GetDetailMovieController createMovieResultUseCase(
            ViewManagerModel viewManagerModel,
            WithoutFilterViewModel withoutFilterViewModel,
            WithoutFilterResultViewModel withoutFilterResultViewModel,
            GetDetailMovieViewModel getDetailMovieViewModel,
            GetDetailMovieDataAccessInterface detailMovieDataAccessInterface) throws IOException {
        GetDetailMovieOutputBoundary getDetailMovieOutputBoundary = new GetDetailMoviePresenter(getDetailMovieViewModel, viewManagerModel);

        UserFactory userFactory = new CommonUserFactory();
        GetDetailMovieInputBoundary getDetailInteractor = new GetDetailMovieInteractor(getDetailMovieOutputBoundary, detailMovieDataAccessInterface);
        return new GetDetailMovieController(getDetailInteractor);
    }
}


