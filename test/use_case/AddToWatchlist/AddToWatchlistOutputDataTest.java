package use_case.AddToWatchlist;

import entity.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddToWatchlistOutputDataTest {

    @Test
    void getMessage() {
        Movie movie = new Movie("name", 1);
        AddToWatchlistOutputData outputData = new AddToWatchlistOutputData(movie);
        assertEquals("add name successfully", outputData.getMessage());
    }

    @Test
    void getMovieName() {
        Movie movie = new Movie("name", 1);
        AddToWatchlistOutputData outputData = new AddToWatchlistOutputData(movie);
        assertEquals("name", outputData.getMovieName());
    }
}