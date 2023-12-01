
package use_case.ShareWatchlist;

import entity.Watchlist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShareWatchlistOutputDataTest {

    @Test
    void getUserName() {
        ShareWatchlistOutputData outputData = new ShareWatchlistOutputData("name");
        assertEquals("name", outputData.getUserName());
    }
}