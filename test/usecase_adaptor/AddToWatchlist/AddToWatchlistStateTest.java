package usecase_adaptor.AddToWatchlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddToWatchlistStateTest {

    @Test
    void testState() {
        AddToWatchlistState state = new AddToWatchlistState();
        state.setMessage("message");
        state.setMovieExistError("error");
        assertEquals("message", state.getMessage());
        assertEquals("error", state.getMovieExistError());
    }
}