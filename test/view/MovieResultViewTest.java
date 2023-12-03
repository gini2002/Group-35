package view;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameController;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class MovieResultViewTest {

    @Test
    void updateViewWithValidRecommendedMoviesShouldUpdateListModel() {
        // Arrange
        SwingUtilities.invokeLater(() -> {
            // Creating mock objects
            MovieResultViewModel movieResultViewModel = Mockito.mock(MovieResultViewModel.class);
            SearchByNameViewModel searchByNameViewModel = Mockito.mock(SearchByNameViewModel.class);
            ViewManagerModel viewManagerModel = Mockito.mock(ViewManagerModel.class);
            GetDetailMovieController getDetailMovieController = Mockito.mock(GetDetailMovieController.class);

            // Mocking the behavior of movieResultViewModel
            String[] recommendedMovies = {"Movie1", "Movie2", "Movie3"};
            when(searchByNameViewModel.getRecommendedMovies()).thenReturn(recommendedMovies);
            when(movieResultViewModel.getError()).thenReturn(null);

            // Creating a mock PropertyChangeEvent
            PropertyChangeEvent event = new PropertyChangeEvent(this, "recommendedMovies", null, recommendedMovies);

            // Creating the MovieResultView instance
            MovieResultView movieResultView = new MovieResultView(movieResultViewModel, searchByNameViewModel, viewManagerModel, getDetailMovieController);

            // Act
            movieResultView.propertyChange(event);

            // Assert
            DefaultListModel<String> listModel = movieResultView.listModel;
            assertEquals(recommendedMovies.length, listModel.size());
            for (int i = 0; i < recommendedMovies.length; i++) {
                assertEquals(recommendedMovies[i], listModel.get(i));
            }

            // Verify that the error label is updated
            assertNull(movieResultView.errorLabel.getText());
        });
    }

    @Test
    void updateViewWithNullRecommendedMoviesShouldNotUpdateListModel() {
        // Arrange
        SwingUtilities.invokeLater(() -> {
            // Creating mock objects
            MovieResultViewModel movieResultViewModel = Mockito.mock(MovieResultViewModel.class);
            SearchByNameViewModel searchByNameViewModel = Mockito.mock(SearchByNameViewModel.class);
            ViewManagerModel viewManagerModel = Mockito.mock(ViewManagerModel.class);
            GetDetailMovieController getDetailMovieController = Mockito.mock(GetDetailMovieController.class);

            // Mocking the behavior of movieResultViewModel
            when(searchByNameViewModel.getRecommendedMovies()).thenReturn(null);
            when(movieResultViewModel.getError()).thenReturn("Error message");

            // Creating a mock PropertyChangeEvent
            PropertyChangeEvent event = new PropertyChangeEvent(this, "recommendedMovies", null, null);

            // Creating the MovieResultView instance
            MovieResultView movieResultView = new MovieResultView(movieResultViewModel, searchByNameViewModel, viewManagerModel, getDetailMovieController);

            // Act
            movieResultView.propertyChange(event);

            // Assert
            DefaultListModel<String> listModel = movieResultView.listModel;
            assertEquals(0, listModel.size()); // List model should not be updated

            // Verify that the error label is updated
            assertEquals("Error message", movieResultView.errorLabel.getText());
        });
    }
}