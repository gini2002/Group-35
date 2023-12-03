package use_case.DeleteWatchlist;

import entity.Movie;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteWatchlistOutputTest {

    @Test
    void getMessage() {
        Movie movie = new Movie("name", 1);
        DeleteWatchlistOutputData outputData = new DeleteWatchlistOutputData(movie);
        assertEquals("delete name successfully", outputData.getMessage());
    }

    @Test
    void getMovieName() {
        Movie movie = new Movie("name", 1);
        DeleteWatchlistOutputData outputData = new DeleteWatchlistOutputData(movie);
        assertEquals("name", outputData.getMovieName());
    }
}

