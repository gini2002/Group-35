package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    //@BeforeEach
    //void init() {
    public String name = "name";
    public int id = 1;
    public ArrayList<String> genre = new ArrayList<>(java.util.Arrays.asList("a"));

    public LocalDate date = LocalDate.of(2023, 11, 27);
    public double rating = 1.11;
    public String poster = "path";
    public String overview = "overview";

    public Movie movie = new Movie(name, id, genre, date, rating, poster, overview);



    @Test
    void getOverview() {
        assertEquals("overview", movie.getOverview());
    }

    @Test
    void getPoster_path() {
        assertEquals("path", movie.getPoster_path());
    }

    @Test
    void getName() {
        assertEquals("name", movie.getName());
    }

    @Test
    void getGenre() {
        assertEquals(new ArrayList<>(java.util.Arrays.asList("a")), movie.getGenre());
    }

    @Test
    void getID() {
        assertEquals(1, movie.getID());
    }

    @Test
    void getReleaseDate() {
        assertEquals(LocalDate.of(2023, 11, 27), movie.getReleaseDate());
    }

    @Test
    void getRating() {
        assertEquals(1.11, movie.getRating());
    }

    @Test
    void builder() {
        Movie.MovieBuilder builder = Movie.builder()
                .id(1)
                .name("name")
                .genre(new ArrayList<>(java.util.Arrays.asList("a")))
                .overview("overview")
                .poster_path("path")
                .rating(1.11);
        Movie movie2 = builder.build();
        assertEquals(movie, movie2);
    }
}