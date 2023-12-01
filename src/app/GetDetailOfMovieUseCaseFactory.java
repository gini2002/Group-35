package app;

import use_case.AddToWatchlist.*;
import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.AddToWatchlist.AddToWatchlistPresenter;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import view.GetDetailMovieView;

public class GetDetailOfMovieUseCaseFactory {
    private GetDetailOfMovieUseCaseFactory() {}

    public static GetDetailMovieView create(GetDetailMovieViewModel getDetailMovieViewModel,
                                            AddToWatchlistDataAccessInterface addToWatchlistDAO,
                                            AddToWatchlistViewModel addToWatchlistViewModel) {
        AddToWatchlistController addToWatchlistController = createAddToWatchlistController(addToWatchlistViewModel, addToWatchlistDAO);
        return new GetDetailMovieView(getDetailMovieViewModel, addToWatchlistController, addToWatchlistViewModel);
    }


    private static AddToWatchlistController createAddToWatchlistController(AddToWatchlistViewModel addToWatchlistViewModel,
                                                                           AddToWatchlistDataAccessInterface DAO) {
        AddToWatchlistOutputBoundary presenter = new AddToWatchlistPresenter(addToWatchlistViewModel);
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(presenter, DAO);
        return new AddToWatchlistController(interactor);
    }
}
