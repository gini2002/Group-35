package data_access;

import entity.Movie;

import java.util.List;

public interface MovieDataAccessInterface {
    List<Movie> searchMoviesByKeyword(String keyword);
    void addToSearchHistory(String userId, String keyword);
}



