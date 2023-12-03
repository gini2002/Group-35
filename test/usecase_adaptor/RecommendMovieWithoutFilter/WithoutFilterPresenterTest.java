package usecase_adaptor.RecommendMovieWithoutFilter;


        import org.junit.jupiter.api.Test;
        import static org.mockito.Mockito.*;

        import use_case.RecommendMovieWithoutFilter.WithoutFilterOutputData;
        import usecase_adaptor.ViewManagerModel;
        import java.util.List;
        import entity.Movie;

class WithoutFilterPresenterTest {

    @Test
    void WithoutFilterSuccessView_UpdatesViewModelsAndViewManager() {
        // Arrange
        WithoutFilterViewModel withoutFilterViewModel = mock(WithoutFilterViewModel.class);
        WithoutFilterResultViewModel withoutFilterResultViewModel = mock(WithoutFilterResultViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        WithoutFilterPresenter presenter = new WithoutFilterPresenter(viewManagerModel, withoutFilterViewModel, withoutFilterResultViewModel);

        List<Movie> movies = List.of(new Movie("Movie1"), new Movie("Movie2"));
        WithoutFilterOutputData outputData = new WithoutFilterOutputData(movies);

        // Act
        presenter.WithoutFilterSuccessView(outputData);

        // Assert
        verify(withoutFilterViewModel, times(1)).setWithoutFilterMovies(movies);
        verify(withoutFilterViewModel, times(1)).firePropertyChanged();
        verify(withoutFilterResultViewModel, times(1)).setWithoutFilterMovies(movies);
        verify(withoutFilterResultViewModel, times(1)).firePropertyChanged();
        verify(viewManagerModel, times(1)).setActiveView(withoutFilterViewModel.getViewName());
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    void WithoutFilterFailView_UpdatesViewModelWithError() {
        // Arrange
        WithoutFilterViewModel withoutFilterViewModel = mock(WithoutFilterViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);

        WithoutFilterPresenter presenter = new WithoutFilterPresenter(viewManagerModel, withoutFilterViewModel, null);

        String error = "Error message";

        // Act
        presenter.WithoutFilterFailView(error);

        // Assert
        verify(withoutFilterViewModel, times(1)).setError(error);
        verify(withoutFilterViewModel, times(1)).firePropertyChanged();
    }
}
