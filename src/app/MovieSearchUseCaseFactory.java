package app;

import use_case.MovieSearchByKeyword.RecommendInputBoundary;
import use_case.ShareWatchlist.ShareWatchlistDataAccessInterface;
import use_case.ShareWatchlist.ShareWatchlistInputBoundary;
import use_case.ShareWatchlist.ShareWatchlistInteractor;
import use_case.ShareWatchlist.ShareWatchlistOutputBoundary;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistController;
import usecase_adaptor.ShareWatchlist.ShareWatchlistPresenter;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
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
public class MovieSearchUseCaseFactory {
    private MovieSearchUseCaseFactory() {}

    public static MovieRecommendView create(
            ViewManagerModel viewManagerModel,
            SearchByNameViewModel viewModel,
            MovieResultViewModel resultViewModel,
            SearchByNameDataAccessInterface userDataAccessObject,
            ShareWatchlistViewModel shareWatchlistViewModel,
            ShareWatchlistDataAccessInterface shareWatchlistDataAccessObject) {
        try {
            SearchByNameController controller = createMovieSearchUseCase(viewManagerModel, viewModel, resultViewModel, userDataAccessObject);
            ShareWatchlistController shareWatchlistController = createShareWatchlistController(shareWatchlistViewModel, shareWatchlistDataAccessObject);
            return new MovieRecommendView(viewModel, controller, shareWatchlistController, shareWatchlistViewModel);
        } catch (IOException e) {
            // Handle the exception more gracefully (log it, show a user-friendly message, etc.).
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not open movie data file.");
        }

        return null;
    }

    private static SearchByNameController createMovieSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchByNameViewModel viewModel,
            MovieResultViewModel resultViewModel,
            SearchByNameDataAccessInterface userDataAccessObject) throws IOException {
        SearchByNameOutputBoundary searchByNameOutputBoundary = new SearchByNamePresenter(viewManagerModel, viewModel, resultViewModel);

        UserFactory userFactory = new CommonUserFactory();

        RecommendInputBoundary searchByNameInteractor = new SearchByNameInteractor(userDataAccessObject, searchByNameOutputBoundary);

        return new SearchByNameController(searchByNameInteractor);

    }

    private static ShareWatchlistController createShareWatchlistController(ShareWatchlistViewModel viewModel,
                                                                           ShareWatchlistDataAccessInterface dataAccessInterface) {
        ShareWatchlistOutputBoundary outputBoundary = new ShareWatchlistPresenter(viewModel); // need view model
        ShareWatchlistInputBoundary interactor = new ShareWatchlistInteractor(outputBoundary, dataAccessInterface);
        return new ShareWatchlistController(interactor);
    }


}