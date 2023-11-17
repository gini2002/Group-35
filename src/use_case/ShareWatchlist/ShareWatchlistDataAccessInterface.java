package use_case.ShareWatchlist;

import java.util.List;
import entity.Movie;

public interface ShareWatchlistDataAccessInterface {
    boolean userExist(String userName);

    public List<Movie> getWatchlist(String userName);

    public void setWatchlist(String userName, List<Movie> watchlist);
}
