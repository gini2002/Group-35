package use_case;

import entity.Movie;

import java.util.List;

public interface SearchByNameDataAccessInterface {
    List<Movie> getRecommendedMovies(String keyword);
//    List<Movie> searchMoviesByKeyword(String keyword);
//    void addToSearchHistory(String userId, String keyword)
}
