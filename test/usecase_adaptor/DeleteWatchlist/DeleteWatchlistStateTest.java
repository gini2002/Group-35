package usecase_adaptor.DeleteWatchlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeleteWatchlistStateTest {

    @Test
    void testState() {
        DeleteWatchlistState state = new DeleteWatchlistState();

        // Test setting and getting the message
        String testMessage = "Delete successful";
        state.setMessage(testMessage);
        assertEquals(testMessage, state.getMessage());

        // Test setting and getting the movie existence error
        String testError = "Movie not found";
        state.setMovieExistError(testError);
        assertEquals(testError, state.getMovieExistError());
    }
}
