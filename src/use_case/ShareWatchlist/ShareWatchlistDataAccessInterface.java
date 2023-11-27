package use_case.ShareWatchlist;

import java.util.List;
import entity.Movie;
import entity.User;

public interface ShareWatchlistDataAccessInterface {
    boolean userExist(String userName);

    public List<Movie> getWatchlistByUsername(String userName);

    public void setWatchlist(String userName, List<Movie> watchlist);

    User getUser(String userName);
}
