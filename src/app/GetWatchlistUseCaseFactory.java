package app;

import use_case.GetDetailMovie.*;
import use_case.GetWatchList.*;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.GetDetailOfMovie.GetDetailMoviePresenter;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.GetWatchlist.GetWatchlistController;
import usecase_adaptor.GetWatchlist.GetWatchlistPresenter;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ViewManagerModel;
import view.GetWatchlistView;

public class GetWatchlistUseCaseFactory {
    private GetWatchlistUseCaseFactory(){}

    public static GetWatchlistView create(ViewManagerModel viewManagerModel,
                                          GetWatchListDataAccessInterface getWatchListDataAccessInterface,
                                          GetDetailMovieViewModel getDetailMovieViewModel,
                                          GetWatchListViewmodel getWatchListViewmodel,
                                          GetDetailMovieDataAccessInterface getDetailMovieDataAccessInterface,
                                          MainMenuViewModel mainMenuViewModel) {
        GetDetailMovieController getDetailMovieController = createGetDetailMovieController(
                getDetailMovieViewModel, getDetailMovieDataAccessInterface, viewManagerModel);
        return new GetWatchlistView(getWatchListViewmodel,
                getDetailMovieController, getDetailMovieViewModel, viewManagerModel, mainMenuViewModel);
    }

    private static GetDetailMovieController createGetDetailMovieController(GetDetailMovieViewModel getDetailMovieViewModel,
                                                                           GetDetailMovieDataAccessInterface DAO,
                                                                           ViewManagerModel viewManagerModel){
        GetDetailMovieOutputBoundary presenter = new GetDetailMoviePresenter(getDetailMovieViewModel, viewManagerModel);
        GetDetailMovieInputBoundary interactor = new GetDetailMovieInteractor(presenter, DAO);
        return new GetDetailMovieController(interactor);
    }
}
