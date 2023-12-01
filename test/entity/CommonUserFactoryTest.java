package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserFactoryTest {

    @Test
    void create() {
        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1,1,1,1,1));
        assertEquals("name", user.getName());
        assertEquals("password", user.getPassword());
        assertEquals(LocalDateTime.of(1,1,1,1,1), user.getCreationTime());
    }

    @Test
    void testCreate() {
        SearchHistory searchHistory = new SearchHistory();
        Watchlist watchlist = new Watchlist();

        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1,1,1,1,1),
                searchHistory, watchlist);

        assertEquals("name", user.getName());
        assertEquals("password", user.getPassword());
        assertEquals(LocalDateTime.of(1,1,1,1,1), user.getCreationTime());
        assertEquals(searchHistory.getSearchHistory(), user.getSearchHistory());
        assertEquals(watchlist.getWatchlist(), user.getWatchlist());
    }
}