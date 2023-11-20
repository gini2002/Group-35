package app;

import javax.swing.*;
import java.awt.*;

import data_access.MovieDataAccessObject;
import entity.CommonUserFactory;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
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

        MovieDataAccessObject movieDataAccessObject;

        movieDataAccessObject = new MovieDataAccessObject(searchByNameViewModel.getKeywordInput(), new CommonUserFactory());

        MovieRecommendView movieRecommendView = MovieSearchUseCaseFactory.create(viewManagerModel, searchByNameViewModel, resultViewModel, movieDataAccessObject);
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