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

public class WithoutFilterResultUseCaseFactory {

    private WithoutFilterResultUseCaseFactory() {}

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


