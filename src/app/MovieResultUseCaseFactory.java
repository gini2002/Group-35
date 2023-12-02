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
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;
import view.MovieResultView;

import java.io.IOException;


public class MovieResultUseCaseFactory {
    private MovieResultUseCaseFactory() {}

    public static MovieResultView create(
            ViewManagerModel viewManagerModel,
            SearchByNameViewModel searchByNameViewModel,
            MovieResultViewModel movieResultViewModel,
            GetDetailMovieViewModel getDetailMovieViewModel,
            GetDetailMovieDataAccessInterface detailMovieDataAccessInterface) {
        try {
            GetDetailMovieController getDetailMovieController = createMovieResultUseCase(viewManagerModel, searchByNameViewModel, movieResultViewModel, getDetailMovieViewModel, detailMovieDataAccessInterface);
            return new MovieResultView(movieResultViewModel, searchByNameViewModel, viewManagerModel, getDetailMovieController);
        } catch (Exception e) {
            // Handle the exception (log it, show a user-friendly message, etc.)
            e.printStackTrace();
            return null;
        }
    }

    private static GetDetailMovieController createMovieResultUseCase(
            ViewManagerModel viewManagerModel,
            SearchByNameViewModel searchByNameViewModel,
            MovieResultViewModel movieResultViewModel,
            GetDetailMovieViewModel getDetailMovieViewModel,
            GetDetailMovieDataAccessInterface detailMovieDataAccessInterface) throws IOException {
        GetDetailMovieOutputBoundary getDetailMovieOutputBoundary = new GetDetailMoviePresenter(getDetailMovieViewModel);

        UserFactory userFactory = new CommonUserFactory();
        GetDetailMovieInputBoundary getDetailInteractor = new GetDetailMovieInteractor(getDetailMovieOutputBoundary, detailMovieDataAccessInterface);
        return new GetDetailMovieController(getDetailInteractor);
    }


}
