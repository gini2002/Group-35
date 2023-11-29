package view;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void propertyChange_RecommendedMoviesPropertySet_UpdatesView() {
        // Arrange
        MovieResultViewModel viewModel = mock(MovieResultViewModel.class);
        SearchByNameViewModel searchByNameViewModel = mock(SearchByNameViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        MovieResultView movieResultView = new MovieResultView(viewModel, searchByNameViewModel, viewManagerModel);

        // Act
        movieResultView.propertyChange(new PropertyChangeEvent(this, "recommendedMovies", null, null));

        // Assert
        // Verify that updateView() is called
        // (This could be extended based on the actual implementation)
        verify(movieResultView, times(1)).updateView();
    }

    @Test
    void propertyChange_ErrorPropertySet_DoesNotUpdateView() {
        // Arrange
        MovieResultViewModel viewModel = mock(MovieResultViewModel.class);
        SearchByNameViewModel searchByNameViewModel = mock(SearchByNameViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        MovieResultView movieResultView = new MovieResultView(viewModel, searchByNameViewModel, viewManagerModel);

        // Act
        movieResultView.propertyChange(new PropertyChangeEvent(this, "error", null, "Invalid keyword"));

        // Assert
        // Verify that updateView() is not called for error property change
        verify(movieResultView, times(0)).updateView();
    }

    @Test
    void updateView_RecommendedMoviesNotNull_UpdatesListModelAndRepaintsUI() {
        // Arrange
        MovieResultViewModel viewModel = mock(MovieResultViewModel.class);
        SearchByNameViewModel searchByNameViewModel = mock(SearchByNameViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        MovieResultView movieResultView = new MovieResultView(viewModel, searchByNameViewModel, viewManagerModel);
        movieResultView.listModel = spy(new DefaultListModel<>());

        // Mock the getRecommendedMovies() method
        when(searchByNameViewModel.getRecommendedMovies()).thenReturn(new String[]{"Movie1", "Movie2"});

        // Act
        movieResultView.updateView();

        // Assert
        // Verify that listModel is cleared and updated
        verify(movieResultView.listModel, times(1)).clear();
        verify(movieResultView.listModel, times(1)).addAll(Arrays.asList("Movie1", "Movie2"));
        verify(movieResultView.movieList, times(1)).setModel(movieResultView.listModel);

        // Verify that errorLabel is updated
        verify(movieResultView.errorLabel, times(1)).setText(anyString());

        // Verify that UI is repainted
        verify(movieResultView, times(1)).revalidate();
        verify(movieResultView, times(1)).repaint();
    }

    @Test
    void updateView_RecommendedMoviesNull_DoesNotUpdateListModel() {
        // Arrange
        MovieResultViewModel viewModel = mock(MovieResultViewModel.class);
        SearchByNameViewModel searchByNameViewModel = mock(SearchByNameViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        MovieResultView movieResultView = new MovieResultView(viewModel, searchByNameViewModel, viewManagerModel);
        movieResultView.listModel = spy(new DefaultListModel<>());

        // Mock the getRecommendedMovies() method
        when(searchByNameViewModel.getRecommendedMovies()).thenReturn(null);

        // Act
        movieResultView.updateView();

        // Assert
        // Verify that listModel is not cleared or updated
        verify(movieResultView.listModel, times(0)).clear();
        verify(movieResultView.listModel, times(0)).addAll(anyList());
        verify(movieResultView.movieList, times(0)).setModel(movieResultView.listModel);

        // Verify that errorLabel is updated
        verify(movieResultView.errorLabel, times(1)).setText(anyString());

        // Verify that UI is repainted
        verify(movieResultView, times(1)).revalidate();
        verify(movieResultView, times(1)).repaint();
    }
}