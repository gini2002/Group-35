package app;

import use_case.ShareWatchlist.ShareWatchlistDataAccessInterface;
import use_case.ShareWatchlist.ShareWatchlistInputBoundary;
import use_case.ShareWatchlist.ShareWatchlistInteractor;
import use_case.ShareWatchlist.ShareWatchlistOutputBoundary;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistController;
import usecase_adaptor.ShareWatchlist.ShareWatchlistPresenter;
import usecase_adaptor.ShareWatchlist.ShareWatchlistState;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
import usecase_adaptor.ViewManagerModel;
import view.ShareWatchlistView;

import java.io.IOException;

public class ShareWatchlistUseCaseFactory {

    private ShareWatchlistUseCaseFactory(){}

    public static ShareWatchlistView create(ViewManagerModel viewManagerModel,
                                            ShareWatchlistViewModel shareWatchlistViewModel,
                                            ShareWatchlistDataAccessInterface dataAccessInterface,
                                            MainMenuViewModel mainMenuViewModel
                                            ) {
        //try {
            ShareWatchlistController controller = createController(dataAccessInterface, shareWatchlistViewModel);
            return new ShareWatchlistView(shareWatchlistViewModel, controller,viewManagerModel, mainMenuViewModel);
        //} catch (IOException e) {
        //    throw new RuntimeException();
        //}
    }

    private static ShareWatchlistController createController(ShareWatchlistDataAccessInterface DAO,
                                                                ShareWatchlistViewModel viewModel) {
        ShareWatchlistOutputBoundary presenter = new ShareWatchlistPresenter(viewModel);
        ShareWatchlistInputBoundary interactor = new ShareWatchlistInteractor(presenter, DAO);
        return new ShareWatchlistController(interactor);
    }
}
