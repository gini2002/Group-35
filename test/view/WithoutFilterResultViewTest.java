package view;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterResultViewModel;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WithoutFilterResultViewTest {

    @Test
    void updateViewWithValidRecommendedMoviesShouldUpdateListModel() {
        // Arrange
        SwingUtilities.invokeLater(() -> {
            WithoutFilterResultViewModel resultViewModel = Mockito.mock(WithoutFilterResultViewModel.class);
            WithoutFilterViewModel withoutFilterViewModel = Mockito.mock(WithoutFilterViewModel.class);
            ViewManagerModel viewManagerModel = Mockito.mock(ViewManagerModel.class);
            GetDetailMovieController getDetailMovieController = Mockito.mock(GetDetailMovieController.class);

            // Mocking the behavior of withoutFilterViewModel
            String[] recommendedMovies = {"Movie1", "Movie2", "Movie3"};
            when(withoutFilterViewModel.getRecommendedMovies()).thenReturn(recommendedMovies);

            // Creating a mock PropertyChangeEvent
            PropertyChangeEvent event = new PropertyChangeEvent(this, "withoutFilterMovies", null, recommendedMovies);

            // Creating the WithoutFilterResultView instance
            WithoutFilterResultView view = new WithoutFilterResultView(resultViewModel, withoutFilterViewModel, viewManagerModel, getDetailMovieController);

            // Act
            view.propertyChange(event);

            // Assert
            DefaultListModel<String> listModel = view.listModel;
            assertEquals(recommendedMovies.length, listModel.size());
            for (int i = 0; i < recommendedMovies.length; i++) {
                assertEquals(recommendedMovies[i], listModel.get(i));
            }
        });
    }

    @Test
    void updateViewWithErrorShouldDisplayErrorMessage() {
        // Arrange
        SwingUtilities.invokeLater(() -> {
            WithoutFilterResultViewModel resultViewModel = Mockito.mock(WithoutFilterResultViewModel.class);
            WithoutFilterViewModel withoutFilterViewModel = Mockito.mock(WithoutFilterViewModel.class);
            ViewManagerModel viewManagerModel = Mockito.mock(ViewManagerModel.class);
            GetDetailMovieController getDetailMovieController = Mockito.mock(GetDetailMovieController.class);

            // Mocking the behavior of resultViewModel
            String error = "Error message";
            when(resultViewModel.getError()).thenReturn(error);

            // Creating a mock PropertyChangeEvent
            PropertyChangeEvent event = new PropertyChangeEvent(this, "error", null, error);

            // Creating the WithoutFilterResultView instance
            WithoutFilterResultView view = new WithoutFilterResultView(resultViewModel, withoutFilterViewModel, viewManagerModel, getDetailMovieController);

            // Act
            view.propertyChange(event);

            // Assert
            assertEquals(error, view.errorLabel.getText());
        });
    }

    // Additional tests can be added for handling clicks on the movie list, testing the main menu button functionality, etc.
}

