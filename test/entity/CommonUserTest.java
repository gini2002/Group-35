package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {

    @Test
    void getId() {
        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1, 1, 1, 1, 1));
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    void getName() {
        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals("name", user.getName());
    }

    @Test
    void getPassword() {
        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals("password", user.getPassword());
    }

    @Test
    void getCreationTime() {
        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals(LocalDateTime.of(1, 1, 1, 1, 1), user.getCreationTime());
    }

    @Test
    void getSearchHistory() {
        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1, 1, 1, 1, 1));
        //TODO
    }

    @Test
    void getWatchlist() {
        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1, 1, 1, 1, 1));
        Movie movie = new Movie("movie", 1);
        user.addMovieToWatchlist(movie);
        assertEquals(1, user.getWatchlist().get(0).getID());
    }

    @Test
    void getSharedWatchlist() {
        //receiver
        //sender+watchlist
        User receiver = new CommonUserFactory().create("receiver",
                "password", LocalDateTime.of(1, 1, 1, 1, 1));
        Watchlist watchlist = new Watchlist();
        Movie movie = new Movie("name", 1);
        watchlist.addMovie(movie);
        receiver.setSharedWatchlist("sender", watchlist);
        assertEquals(watchlist.getWatchlist(), receiver.getSharedWatchlist("sender"));
    }

    @Test
    void testGetSharedWatchlist() {
        User receiver = new CommonUserFactory().create("receiver",
                "password", LocalDateTime.of(1, 1, 1, 1, 1));
        Watchlist watchlist = new Watchlist();
        Map<String, Watchlist> shared = new HashMap<>();
        Movie movie = new Movie("name", 1);
        watchlist.addMovie(movie);
        shared.put("sender", watchlist);
        receiver.setCompleteSharedWatchlist(shared);
        assertEquals(shared, receiver.getSharedWatchlist());
    }


}