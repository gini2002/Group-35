package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

//    List<Movie> addMovies(String keyword);

    List<Movie> getSearchHistory();

    List<Movie> getWatchlist();

    List<Movie> getSharedWatchlist(String userName);

    void setSharedWatchlist(String userName, Watchlist watchlist);

    void addMovieToWatchlist(Movie movie);




}