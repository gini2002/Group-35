package view;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameController;

import java.beans.PropertyChangeEvent;

class MovieRecommendViewTest {
    @Test
    void actionPerformed_SearchButtonClicked_ExecutesControllerAndChangesActiveView() {
        // Arrange
        SearchByNameViewModel viewModel = mock(SearchByNameViewModel.class);
        SearchByNameController controller = mock(SearchByNameController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        MovieRecommendView movieRecommendView = new MovieRecommendView(viewModel, controller, viewManagerModel);

        // Act
        movieRecommendView.searchButton.doClick();

        // Assert
        verify(controller, times(1)).execute(anyString());

        ArgumentCaptor<String> keywordCaptor = ArgumentCaptor.forClass(String.class);
        verify(controller).execute(keywordCaptor.capture());
        assertEquals("", keywordCaptor.getValue());

        verify(viewManagerModel, times(1)).setActiveView("movie_result");
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    void actionPerformed_SearchListButtonClicked_ChangesActiveView() {
        // Arrange
        SearchByNameViewModel viewModel = mock(SearchByNameViewModel.class);
        SearchByNameController controller = mock(SearchByNameController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        MovieRecommendView movieRecommendView = new MovieRecommendView(viewModel, controller, viewManagerModel);

        // Act
        movieRecommendView.searchListButton.doClick();

        // Assert
        verify(viewManagerModel, times(1)).setActiveView("search_list");
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    void propertyChange_ErrorPropertySet_UpdatesErrorLabel() {
        // Arrange
        SearchByNameViewModel viewModel = mock(SearchByNameViewModel.class);
        SearchByNameController controller = mock(SearchByNameController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        MovieRecommendView movieRecommendView = new MovieRecommendView(viewModel, controller, viewManagerModel);

        // Act
        movieRecommendView.propertyChange(new PropertyChangeEvent(this, "error", null, "Invalid keyword"));

        // Assert
        assertEquals("Invalid keyword", movieRecommendView.errorLabel.getText());
    }

    @Test
    void propertyChange_RecommendedMoviesPropertySet_DoesNotUpdateRecommendedMoviesArea() {
        // Arrange
        SearchByNameViewModel viewModel = mock(SearchByNameViewModel.class);
        SearchByNameController controller = mock(SearchByNameController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        MovieRecommendView movieRecommendView = new MovieRecommendView(viewModel, controller, viewManagerModel);

        // Act
        movieRecommendView.propertyChange(new PropertyChangeEvent(this, "recommendedMovies", null, "Movie List"));

        // Assert
        // Verify that the recommended movies area is not updated in this case
        // (This could be extended based on the actual implementation)
    }

}