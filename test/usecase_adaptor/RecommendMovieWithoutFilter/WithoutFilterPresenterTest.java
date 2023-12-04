package usecase_adaptor.RecommendMovieWithoutFilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import use_case.RecommendMovieWithoutFilter.WithoutFilterOutputData;
import usecase_adaptor.ViewManagerModel;
import java.util.List;
import entity.Movie;

class WithoutFilterPresenterTest {

    private WithoutFilterViewModel withoutFilterViewModel;
    private WithoutFilterResultViewModel withoutFilterResultViewModel;
    private ViewManagerModel viewManagerModel;
    private WithoutFilterPresenter presenter;

    @BeforeEach
    void setUp() {
        withoutFilterViewModel = mock(WithoutFilterViewModel.class);
        withoutFilterResultViewModel = mock(WithoutFilterResultViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        presenter = new WithoutFilterPresenter(viewManagerModel, withoutFilterViewModel, withoutFilterResultViewModel);

        // Mock the behavior of withoutFilterResultViewModel.getState() to return a non-null WithoutFilterResultState
        WithoutFilterResultState mockState = mock(WithoutFilterResultState.class);
        when(withoutFilterResultViewModel.getState()).thenReturn(mockState);
    }

    @Test
    void WithoutFilterSuccessView_UpdatesViewModelsAndViewManager() {
        // Arrange
        List<Movie> movies = List.of(new Movie("Movie1"), new Movie("Movie2"));
        WithoutFilterOutputData outputData = new WithoutFilterOutputData(movies);

        // Act
        presenter.WithoutFilterSuccessView(outputData);

        // Assert
        verify(withoutFilterViewModel, times(1)).setWithoutFilterMovies(movies);
        verify(withoutFilterViewModel, times(1)).firePropertyChanged();
        verify(withoutFilterResultViewModel, times(1)).setWithoutFilterMovies(movies);
        verify(withoutFilterResultViewModel.getState(), times(1)).setWithoutFilterMovies(movies);
        verify(withoutFilterResultViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView(withoutFilterViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    void WithoutFilterFailView_UpdatesViewModelWithError() {
        // Arrange
        String error = "Error message";

        // Act
        presenter.WithoutFilterFailView(error);

        // Assert
        verify(withoutFilterViewModel, times(1)).setError(error);
        verify(withoutFilterResultViewModel.getState(), times(1)).setError(error);
        verify(withoutFilterResultViewModel, times(1)).firePropertyChanged();
        verify(withoutFilterViewModel, times(1)).firePropertyChanged();
    }
}