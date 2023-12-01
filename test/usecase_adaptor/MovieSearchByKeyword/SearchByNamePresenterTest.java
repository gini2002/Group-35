package usecase_adaptor.MovieSearchByKeyword;

import org.junit.jupiter.api.Test;
import use_case.MovieSearchByKeyword.RecommendOutputData;
import usecase_adaptor.SearchList.SearchListViewModel;
import usecase_adaptor.ViewManagerModel;
import entity.Movie;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class SearchByNamePresenterTest {
    @Test
    void prepareSuccessView_UpdatesViewModelsAndViewManager() {
        // Arrange
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        SearchByNameViewModel searchByNameViewModel = mock(SearchByNameViewModel.class);
        MovieResultViewModel movieResultViewModel = mock(MovieResultViewModel.class);
        SearchListViewModel searchListViewModel = mock(SearchListViewModel.class);
        SearchByNamePresenter presenter = new SearchByNamePresenter(viewManagerModel, searchByNameViewModel, movieResultViewModel, searchListViewModel);

        List<Movie> recommendedMovies = Arrays.asList(new Movie("Movie1"), new Movie("Movie2"));

        RecommendOutputData outputData = new RecommendOutputData(recommendedMovies);

        // Act
        presenter.prepareSuccessView(outputData);

        // Assert
        // Verify that the view models are updated with the correct data
        verify(searchByNameViewModel, times(1)).setRecommendedMovies(recommendedMovies);
        verify(movieResultViewModel, times(1)).setRecommendedMovies(recommendedMovies);

        // Verify that the view models' firePropertyChanged methods are called
        verify(searchByNameViewModel, times(1)).firePropertyChanged();
        verify(movieResultViewModel, times(1)).firePropertyChanged();

        // Verify that the view manager is updated with the correct view name
        verify(viewManagerModel, times(1)).setActiveView(searchByNameViewModel.getViewName());

        // Verify that the view manager's firePropertyChanged method is called
        verify(viewManagerModel, times(1)).firePropertyChanged();
    }

    @Test
    void prepareFailView_UpdatesViewModelWithError() {
        // Arrange
        SearchByNameViewModel searchByNameViewModel = mock(SearchByNameViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        SearchByNamePresenter presenter = new SearchByNamePresenter(viewManagerModel, searchByNameViewModel, null, null);
        String error = "Invalid keyword";

        // Act
        presenter.prepareFailView(error);

        // Assert
        // Verify that the view model is updated with the correct error message
        verify(searchByNameViewModel, times(1)).setError(error);

        // Verify that the view model's firePropertyChanged method is called
        verify(searchByNameViewModel, times(1)).firePropertyChanged();
    }

}