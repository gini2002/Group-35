package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    List<Movie> searchMovies(String keyword);

    List<Movie> getSearchHistory();




}