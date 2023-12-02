package usecase_adaptor.ShareWatchlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShareWatchlistStateTest {

    @Test
    void testState() {
        ShareWatchlistState state = new ShareWatchlistState();
        state.setError("error");
        state.setLoggedUserName("user");
        state.setReceiverName("user2");
        assertEquals("error", state.getError());
        assertEquals("user", state.getLoggedUserName());
        assertEquals("user2", state.getReseiverName());
    }
}