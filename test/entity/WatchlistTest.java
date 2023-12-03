package entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WatchlistTest {
    private ArrayList<Movie> movies = new ArrayList<>();
    private Watchlist watchlist;

    String name = "movie";
    int id = 1;
    ArrayList<String> genres = new ArrayList<>();
    LocalDate date = LocalDate.of(2023, 11, 28);
    int rating = 3;
    String poster_path = "url";
    String overview = "overview for movie";

    @Before
    public void init(){
        for (int i = 0; i < 5; i++){
            Movie.MovieBuilder builder = Movie.builder()
                    .id(id + i)
                    .name(name + String.valueOf(i))
                    .genre(genres)
                    .overview(overview + String.valueOf(i))
                    .poster_path(poster_path + String.valueOf(i))
                    .releaseDate(date)
                    .rating(rating + i);
            genres.add("genre" + String.valueOf(i));
            Movie movie = builder.build();
            movies.add(movie);
        }
        watchlist = new Watchlist(movies);
    }

    @Test
    public void testAddMovie(){
        Movie movie = new Movie(name, id, genres, date, rating, poster_path, overview);
        movies.add(movie);
        watchlist.addMovie(movie);
        assert watchlist.getWatchlist().get(5) == movie;
    }
    @Test
    public void testDeleteMovie(){
        Movie movie = new Movie(name, id, genres, date, rating, poster_path, overview);
        movies.add(movie);
        watchlist.addMovie(movie);
        watchlist.deleteMovie(movie);
        assert watchlist.getWatchlist() == movies;
    }

    @Test
    public void testGetWatchlist(){
        assert watchlist.getWatchlist() == movies;
    }

    @Test
    public void testToSting() {
        assertEquals("1#2#3#4#5", watchlist.toString());
    }
    @Test
    public void testEmptyToSting() {
        Watchlist watchlist2 = new Watchlist();
        assertEquals("", watchlist2.toString());
    }
    @Test
    public void testNullToSting() {
        Watchlist watchlist3 = new Watchlist();
        watchlist3.getWatchlist().clear();
        String result = watchlist3.toString();
        assertEquals("", result);
    }
}
