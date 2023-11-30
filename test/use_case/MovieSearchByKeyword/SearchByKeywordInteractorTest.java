package use_case.MovieSearchByKeyword;

import entity.Movie;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.MovieSearchByKeyword.SearchByNameDataAccessInterface;
import data_access.MovieDataAccessObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchByKeywordInteractorTest {
    @Test
    void getRecommendedMovies_Success() {
        // Mock the UserFactory
        UserFactory userFactory = mock(UserFactory.class);

        // Mock the SearchByNameDataAccessInterface
        SearchByNameDataAccessInterface dataAccessObject = mock(MovieDataAccessObject.class);
        when(dataAccessObject.getRecommendedMovies("action")).thenReturn(List.of(new Movie("Inception"), new Movie("The Dark Knight")));

        // Instantiate MovieDataAccessObject with the mocked dependencies
        MovieDataAccessObject movieDataAccessObject = new MovieDataAccessObject("action", userFactory);

        // Call the method under test
        List<Movie> recommendedMovies = movieDataAccessObject.getRecommendedMovies("action");

        // Verify the result
        assertEquals(2, recommendedMovies.size());
        assertEquals("Inception", recommendedMovies.get(0).getName());
        assertEquals("The Dark Knight", recommendedMovies.get(1).getName());
    }

    @Test
    void getRecommendedMovies_EmptyList() {
        // Mock the UserFactory
        UserFactory userFactory = mock(UserFactory.class);

        // Mock the SearchByNameDataAccessInterface
        SearchByNameDataAccessInterface dataAccessObject = mock(MovieDataAccessObject.class);
        when(dataAccessObject.getRecommendedMovies("unknown")).thenReturn(List.of());

        // Instantiate MovieDataAccessObject with the mocked dependencies
        MovieDataAccessObject movieDataAccessObject = new MovieDataAccessObject("unknown", userFactory);

        // Call the method under test
        List<Movie> recommendedMovies = movieDataAccessObject.getRecommendedMovies("unknown");

        // Verify the result
        assertEquals(0, recommendedMovies.size());
    }
}