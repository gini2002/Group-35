package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchHistoryTest {

    private SearchHistory searchHistory;
    private Movie movie1, movie2;

    @BeforeEach
    void setUp() {
        movie1 = Movie.builder()
                .id(1)
                .name("Inception")
                .releaseDate(LocalDate.of(2010, 7, 16))
                .rating(8.8)
                .build();

        movie2 = Movie.builder()
                .id(2)
                .name("Interstellar")
                .releaseDate(LocalDate.of(2014, 11, 7))
                .rating(8.6)
                .build();

        searchHistory = new SearchHistory();
    }

    @Test
    void testEmptyConstructor() {
        assertTrue(searchHistory.getSearchHistory().isEmpty());
    }

    @Test
    void testConstructorWithMovies() {
        List<Movie> movies = Arrays.asList(movie1, movie2);
        SearchHistory historyWithMovies = new SearchHistory(movies);
        assertEquals(movies, historyWithMovies.getSearchHistory());
    }

    @Test
    void testAddToSearchHistory() {
        searchHistory.addToSearchHistory(movie1);
        assertEquals(1, searchHistory.getSearchHistory().size());
        assertEquals(movie1, searchHistory.getSearchHistory().get(0));
    }

    @Test
    void testGetSearchHistory() {
        searchHistory.addToSearchHistory(movie1);
        searchHistory.addToSearchHistory(movie2);

        List<Movie> movies = searchHistory.getSearchHistory();
        assertEquals(2, movies.size());
        assertTrue(movies.contains(movie1));
        assertTrue(movies.contains(movie2));
    }

    @Test
    void testToString() {
        searchHistory.addToSearchHistory(movie1);
        searchHistory.addToSearchHistory(movie2);

        String expectedString = "1#2";
        assertEquals(expectedString, searchHistory.toString());
    }
}
