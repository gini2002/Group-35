package view;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterController;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;
import usecase_adaptor.ViewManagerModel;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WithoutFilterViewTest {
    @Test
    void actionPerformed_SearchButtonClicked_ExecutesControllerAndChangesActiveView() {
        // Arrange
        WithoutFilterViewModel viewModel = mock(WithoutFilterViewModel.class);
        WithoutFilterController controller = mock(WithoutFilterController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        WithoutFilterView withoutFilterView = new WithoutFilterView(viewModel, controller, viewManagerModel);

        // Act
        withoutFilterView.searchButton.doClick();

        // Assert
        verify(controller, times(1)).execute(anyString());

        ArgumentCaptor<String> keywordCaptor = ArgumentCaptor.forClass(String.class);
        verify(controller).execute(keywordCaptor.capture());
        assertEquals("", keywordCaptor.getValue());

        verify(viewManagerModel, times(1)).setActiveView("without_result");
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    void actionPerformed_MainMenuButtonClicked_ChangesActiveView() {
        // Arrange
        WithoutFilterViewModel viewModel = mock(WithoutFilterViewModel.class);
        WithoutFilterController controller = mock(WithoutFilterController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        WithoutFilterView withoutFilterView = new WithoutFilterView(viewModel, controller, viewManagerModel);

        // Act
        withoutFilterView.mainMenuBtn.doClick();

        // Assert
        verify(viewManagerModel, times(1)).setActiveView("Main_menu");
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    void propertyChange_ErrorPropertySet_UpdatesErrorLabel() {
        // Arrange
        WithoutFilterViewModel viewModel = mock(WithoutFilterViewModel.class);
        WithoutFilterController controller = mock(WithoutFilterController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        WithoutFilterView withoutFilterView = new WithoutFilterView(viewModel, controller, viewManagerModel);

        // Act
        withoutFilterView.propertyChange(new PropertyChangeEvent(this, "error", null, "Error message"));

        // Assert
        assertEquals("Error message", withoutFilterView.errorLabel.getText());
    }

    @Test
    void propertyChange_WithoutFilterMoviesPropertySet_UpdatesRecommendedMoviesArea() {
        // Arrange
        WithoutFilterViewModel viewModel = mock(WithoutFilterViewModel.class);
        WithoutFilterController controller = mock(WithoutFilterController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        WithoutFilterView withoutFilterView = new WithoutFilterView(viewModel, controller, viewManagerModel);

        // Act
        withoutFilterView.propertyChange(new PropertyChangeEvent(this, "withoutFilterMovies", null, "Updated movie list"));

        // Assert
        // Verify that the without filter movies area is updated with the new movie list
        // This might involve checking a UI component in WithoutFilterView that displays the movie list
    }
}

