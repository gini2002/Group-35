package app;

import use_case.RecommendInputBoundary;
import usecase_adaptor.ViewManagerModel;
import usecase_adaptor.SearchByNameController;
import usecase_adaptor.SearchByNamePresenter;
import usecase_adaptor.SearchByNameViewModel;
import use_case.SearchByNameInteractor;
import use_case.SearchByNameOutputBoundary;
import use_case.SearchByNameDataAccessInterface;
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
            SearchByNameDataAccessInterface userDataAccessObject) {
        try {
            SearchByNameController controller = createMovieSearchUseCase(viewManagerModel, viewModel, userDataAccessObject);
            return new MovieRecommendView(viewModel, controller);
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
            SearchByNameDataAccessInterface userDataAccessObject) throws IOException {
        SearchByNameOutputBoundary searchByNameOutputBoundary = new SearchByNamePresenter(viewManagerModel, viewModel);

        UserFactory userFactory = new CommonUserFactory();

        RecommendInputBoundary searchByNameInteractor = new SearchByNameInteractor(userDataAccessObject, searchByNameOutputBoundary);

        return new SearchByNameController(searchByNameInteractor);

    }


}
