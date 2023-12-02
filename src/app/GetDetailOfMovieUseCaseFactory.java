package app;

import use_case.AddToWatchlist.*;
import use_case.DeleteWatchlist.DeleteWatchlistDataAccessInterface;
import use_case.DeleteWatchlist.DeleteWatchlistInputBoundary;
import use_case.DeleteWatchlist.DeleteWatchlistInteractor;
import use_case.DeleteWatchlist.DeleteWatchlistOutputBoundary;
import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.AddToWatchlist.AddToWatchlistPresenter;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistController;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistPresenter;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistViewModel;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ViewManagerModel;
import view.GetDetailMovieView;

public class GetDetailOfMovieUseCaseFactory {
    private GetDetailOfMovieUseCaseFactory() {}

    public static GetDetailMovieView create(GetDetailMovieViewModel getDetailMovieViewModel,
                                            AddToWatchlistDataAccessInterface addToWatchlistDAO,
                                            AddToWatchlistViewModel addToWatchlistViewModel,
                                            DeleteWatchlistDataAccessInterface deleteWatchlistDAO,
                                            DeleteWatchlistViewModel deleteWatchlistViewModel,
                                            ViewManagerModel viewManagerModel,
                                            MainMenuViewModel mainMenuViewModel) {
        AddToWatchlistController addToWatchlistController = createAddToWatchlistController(addToWatchlistViewModel, addToWatchlistDAO);
        DeleteWatchlistController deleteWatchlistController = createDeleteWatchlistController(deleteWatchlistViewModel, deleteWatchlistDAO);
        return new GetDetailMovieView(getDetailMovieViewModel, addToWatchlistController, addToWatchlistViewModel, deleteWatchlistController, deleteWatchlistViewModel, viewManagerModel, mainMenuViewModel);
    }


    private static AddToWatchlistController createAddToWatchlistController(AddToWatchlistViewModel addToWatchlistViewModel,
                                                                           AddToWatchlistDataAccessInterface DAO) {
        AddToWatchlistOutputBoundary presenter = new AddToWatchlistPresenter(addToWatchlistViewModel);
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(presenter, DAO);
        return new AddToWatchlistController(interactor);
    }

    private static DeleteWatchlistController createDeleteWatchlistController(DeleteWatchlistViewModel deleteWatchlistViewModel,
                                                                             DeleteWatchlistDataAccessInterface DAO) {
        DeleteWatchlistOutputBoundary presenter = new DeleteWatchlistPresenter(deleteWatchlistViewModel);
        DeleteWatchlistInputBoundary interactor = new DeleteWatchlistInteractor(presenter, DAO);
        return new DeleteWatchlistController(interactor);
    }
}
