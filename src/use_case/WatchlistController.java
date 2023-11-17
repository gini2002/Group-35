package use_case;

import data_access.WatchlistDAO;

import java.sql.SQLException;

public class WatchlistController {
    private WatchlistDAO watchlistDAO;

    public WatchlistController(WatchlistDAO watchlistDAO) {
        this.watchlistDAO = watchlistDAO;
    }

    public void deleteMovieFromWatchlist(int userId, int movieId) {
        try {
            watchlistDAO.deleteMovieFromWatchlist(userId, movieId);
            // Additional logic like sending a success response
        } catch (SQLException e) {
            // Handle exceptions, possibly sending an error response
        }
    }
}

