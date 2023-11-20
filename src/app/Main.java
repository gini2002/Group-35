package app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import data_access.MovieDataAccessObject;
import data_access.ShareWatchlistDataAccessObject;
import entity.CommonUserFactory;
import use_case.ShareWatchlist.ShareWatchlistDataAccessInterface;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
import usecase_adaptor.ViewManagerModel;
import view.MovieRecommendView;
//import view.MovieResultView;
//import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import view.MovieResultView;
import view.ViewManager;

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


        ShareWatchlistViewModel shareWatchlistViewModel = new ShareWatchlistViewModel();

        MovieDataAccessObject movieDataAccessObject;
        ShareWatchlistDataAccessInterface shareWatchlistDataAccessObject;

        movieDataAccessObject = new MovieDataAccessObject(searchByNameViewModel.getKeywordInput(), new CommonUserFactory());


        try {
            shareWatchlistDataAccessObject = new ShareWatchlistDataAccessObject("", new CommonUserFactory());
            //TODO csv path
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MovieRecommendView movieRecommendView = MovieSearchUseCaseFactory.create(viewManagerModel, searchByNameViewModel,
                resultViewModel, movieDataAccessObject, shareWatchlistViewModel, shareWatchlistDataAccessObject);
        views.add(movieRecommendView, movieRecommendView.viewName);

        MovieResultView movieResultView = new MovieResultView(resultViewModel, searchByNameViewModel);
        views.add(movieResultView, movieResultView.viewName);

        viewManagerModel.setActiveView(movieRecommendView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setSize(400, 300);
        application.pack();
        application.setVisible(true);


    }
}