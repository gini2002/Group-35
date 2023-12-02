package app;

import use_case.GetWatchList.GetWatchListDataAccessInterface;
import use_case.GetWatchList.GetWatchListInputBoundary;
import use_case.GetWatchList.GetWatchListInteractor;
import use_case.GetWatchList.GetWatchListOutputBoundary;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.GetWatchlist.GetWatchlistController;
import usecase_adaptor.GetWatchlist.GetWatchlistPresenter;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;
import view.MainMenuView;

public class MainmenuUseCaseFactory {
    private  MainmenuUseCaseFactory(){}
    public static MainMenuView create(ViewManagerModel viewManagerModel,
                                      MainMenuViewModel mainMenuViewModel,
                                      ShareWatchlistViewModel shareWatchlistViewModel,
                                      GetWatchListViewmodel getWatchListViewmodel, SearchByNameViewModel searchByNameViewModel,
                                      WithoutFilterViewModel withoutFilterViewModel,
                                      GetWatchListDataAccessInterface getWatchListDataAccessInterface){
        GetWatchlistController getWatchlistController = createGetWatchlistController(getWatchListDataAccessInterface,
                getWatchListViewmodel);
        return new MainMenuView(viewManagerModel, mainMenuViewModel, shareWatchlistViewModel,searchByNameViewModel,withoutFilterViewModel,
                getWatchListViewmodel, getWatchlistController);
    }

    public static GetWatchlistController createGetWatchlistController(GetWatchListDataAccessInterface watchListDataObject,
                                                                      GetWatchListViewmodel getWatchListViewmodel){
        GetWatchListOutputBoundary getWatchListPresenter = new GetWatchlistPresenter(getWatchListViewmodel);
        GetWatchListInputBoundary getWatchListInteractor = new GetWatchListInteractor(watchListDataObject,
                getWatchListPresenter);
        return new GetWatchlistController(getWatchListInteractor);
    }
}
