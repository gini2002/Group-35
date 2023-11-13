package app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import data_access.MovieDataAccessObject;
import entity.CommonUserFactory;
import usecase_adaptor.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;
import view.MovieRecommendView;
import view.ViewManager;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Get Recommended Movie");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchByNameViewModel searchByNameViewModel = new SearchByNameViewModel();

        MovieDataAccessObject movieDataAccessObject;

        movieDataAccessObject = new MovieDataAccessObject();

        MovieRecommendView movieRecommendView = MovieSearchUseCaseFactory.create(viewManagerModel, searchByNameViewModel, movieDataAccessObject);
        views.add(movieRecommendView, movieRecommendView.viewName);

        viewManagerModel.setActiveView(movieRecommendView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);


    }
}
