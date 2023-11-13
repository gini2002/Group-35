package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WatchlistDAO {

    private Connection connection; // Assuming we have a connection to database

    public WatchlistDAO(Connection connection) {
        this.connection = connection;
    }
    // constructs and executes an SQL command to delete the corresponding entry from the watchlist table in the database.
    public void deleteMovieFromWatchlist(int userId, int movieId) throws SQLException {
        String sql = "DELETE FROM watchlist WHERE user_id = ? AND movie_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, movieId);
            statement.executeUpdate();
        }
    }
}
