package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieTest {

    private Movie movie;

    @BeforeEach
    void init() {
        movie = Movie.builder()
                .id(1)
                .name("Inception")
                .genre(new ArrayList<>(Arrays.asList("Sci-Fi", "Action")))
                .releaseDate(LocalDate.of(2010, 7, 16))
                .rating(8.8)
                .poster_path("/inception_poster.jpg")
                .overview("A mind-bending thriller")
                .build();
    }

    @Test
    void getName() {
        assertEquals("Inception", movie.getName());
    }

    @Test
    void getId() {
        assertEquals(1, movie.getID());
    }

    @Test
    void getGenre() {
        assertEquals(Arrays.asList("Sci-Fi", "Action"), movie.getGenre());
    }

    @Test
    void getReleaseDate() {
        assertEquals(LocalDate.of(2010, 7, 16), movie.getReleaseDate());
    }

    @Test
    void getRating() {
        assertEquals(8.8, movie.getRating());
    }

    @Test
    void getPosterPath() {
        assertEquals("/inception_poster.jpg", movie.getPoster_path());
    }

    @Test
    void getOverview() {
        assertEquals("A mind-bending thriller", movie.getOverview());
    }

    @Test
    void builderPattern() {
        Movie anotherMovie = Movie.builder()
                .name("Interstellar")
                .id(2)
                .releaseDate(LocalDate.of(2014, 11, 7))
                .rating(8.6)
                .overview("Space exploration epic")
                .build();

        assertEquals("Interstellar", anotherMovie.getName());
        assertEquals(2, anotherMovie.getID());
        assertEquals(LocalDate.of(2014, 11, 7), anotherMovie.getReleaseDate());
        assertEquals(8.6, anotherMovie.getRating());
        assertEquals(null, anotherMovie.getPoster_path()); // Assuming poster_path is not set
        assertEquals("Space exploration epic", anotherMovie.getOverview());
    }
}