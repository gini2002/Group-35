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




}