package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface User {

    int getId();

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

//    List<Movie> addMovies(String keyword);

    List<Movie> getSearchHistory();

    List<Movie> getWatchlist();

    List<Movie> getSharedWatchlist(String userName);

    Map<String, Watchlist> getSharedWatchlist();

    void setId(int id);

    void setSharedWatchlist(String userName, Watchlist watchlist);

    void setCompleteSharedWatchlist(Map<String, Watchlist> sharedWatchlist);

    void addMovieToWatchlist(Movie movie);




}