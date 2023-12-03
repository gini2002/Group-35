package use_case.RecommendMovieWithoutFilter;

import data_access.MovieDataAccessObject;
import data_access.WithoutFilterDAO;
import entity.Movie;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class WithoutFilterInteractorTest {

    private WithoutFilterDAO withoutFilterDAO;
    private MovieDataAccessObject movieDAO;
    private WithoutFilterOutputBoundary withoutFilterPresenter;
    private WithoutFilterInputBoundary interactor;

    @Before
    public void setUp() {
        withoutFilterDAO = mock(WithoutFilterDAO.class);
        movieDAO = mock(MovieDataAccessObject.class);
        withoutFilterPresenter = mock(WithoutFilterOutputBoundary.class);
        interactor = new WithoutFilterInteractor(withoutFilterPresenter, withoutFilterDAO, movieDAO);
    }

    @Test
    public void whenWatchlistIsEmpty_ShouldFail() {
        when(withoutFilterDAO.getWatchlistMovies("user")).thenReturn(Arrays.asList());

        WithoutFilterInputData inputData = new WithoutFilterInputData("user");
        interactor.execute(inputData);

        verify(withoutFilterPresenter).WithoutFilterFailView("No movies found in the watchlist");
    }

    @Test
    public void whenWatchlistHasMovies_ShouldRecommendMoviesBasedOnTopGenre() {
        when(withoutFilterDAO.getWatchlistMovies("user")).thenReturn(Arrays.asList(1, 2));
        when(withoutFilterDAO.getKeywordsForMovie(1)).thenReturn(Arrays.asList("Action", "Adventure"));
        when(withoutFilterDAO.getKeywordsForMovie(2)).thenReturn(Arrays.asList("Action", "Thriller"));

        List<Movie> recommendedMovies = Arrays.asList(new Movie("Action Movie 1"), new Movie("Action Movie 2"));
        when(movieDAO.getRecommendedMovies("Action")).thenReturn(recommendedMovies);

        WithoutFilterInputData inputData = new WithoutFilterInputData("user");
        interactor.execute(inputData);

        ArgumentCaptor<WithoutFilterOutputData> argumentCaptor = ArgumentCaptor.forClass(WithoutFilterOutputData.class);
        verify(withoutFilterPresenter).WithoutFilterSuccessView(argumentCaptor.capture());

        WithoutFilterOutputData outputData = argumentCaptor.getValue();
        assertEquals(recommendedMovies, outputData.getWithoutFilterMovies());
    }

    // Additional test cases can be written for different scenarios
}
