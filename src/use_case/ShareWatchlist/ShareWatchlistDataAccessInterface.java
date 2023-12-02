package use_case.ShareWatchlist;

import java.util.List;
import entity.Movie;
import entity.User;

public interface ShareWatchlistDataAccessInterface {
    boolean userExist(String userName);

    /**
     *
     * @param userName the name of user.
     * @return the user with userName's watchlist.
     */
    public List<Movie> getWatchlistByUsername(String userName);

    /**
     * save watchlist to user's watchlist.
     * @param userName the name of user.
     * @param watchlist a watchlist.
     */
    public void setWatchlist(String receiverName, String senderName, List<Movie> watchlist);

    /**
     *
     * @param userName of user.
     * @return the user that correspond to userName's user.
     */
    User getUser(String userName);
}
