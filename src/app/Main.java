package app;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

import data_access.*;
import entity.CommonUserFactory;
import entity.Movie;
import entity.MovieFactory;
import use_case.AddToWatchlist.AddToWatchlistDataAccessInterface;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
import usecase_adaptor.ViewManagerModel;
import view.GetDetailMovieView;
import view.MovieRecommendView;
//import view.MovieResultView;
//import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import view.MovieResultView;
import view.ViewManager;
import java.io.IOException;

import data_access.MovieDataAccessObject;
import entity.CommonUserFactory;
import use_case.ShareWatchlist.ShareWatchlistDataAccessInterface;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Movie Recommendations App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchByNameViewModel searchByNameViewModel = new SearchByNameViewModel();
        MovieResultViewModel resultViewModel = new MovieResultViewModel();
        GetDetailMovieViewModel getDetailMovieViewModel = new GetDetailMovieViewModel();
        GetWatchListViewmodel getWatchListViewmodel = new GetWatchListViewmodel();
        ShareWatchlistViewModel shareWatchlistViewModel = new ShareWatchlistViewModel();
        AddToWatchlistViewModel addToWatchlistViewModel = new AddToWatchlistViewModel();

        MovieDataAccessObject movieDataAccessObject;
        ShareWatchlistDataAccessInterface shareWatchlistDataAccessObject;
        AddToWatchlistDataAccessInterface addToWatchlistDataAccessObject;
        movieDataAccessObject = new MovieDataAccessObject(searchByNameViewModel.getKeywordInput(), new CommonUserFactory());

        try {
            shareWatchlistDataAccessObject = new ShareWatchlistDataAccessObject("./userInformation.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            addToWatchlistDataAccessObject = new AddToWatchlistDataAccessObject("./userInformation.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        MovieRecommendView movieRecommendView = MovieSearchUseCaseFactory.create(
                viewManagerModel, searchByNameViewModel, resultViewModel,
                movieDataAccessObject, shareWatchlistViewModel, shareWatchlistDataAccessObject);
        views.add(movieRecommendView, movieRecommendView.viewName);

        GetDetailMovieView getDetailMovieView = GetDetailOfMovieUseCaseFactory.create(
                getDetailMovieViewModel, addToWatchlistDataAccessObject, addToWatchlistViewModel);
        views.add(getDetailMovieView, getDetailMovieView.viewname);

        MovieResultView movieResultView = new MovieResultView(resultViewModel, searchByNameViewModel);
        views.add(movieResultView, movieResultView.viewName);

        viewManagerModel.setActiveView(movieRecommendView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setSize(400, 300);
        application.pack();
        application.setVisible(true);

    }
}