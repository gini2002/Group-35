package error_cases.RecommendMovieWithoutFilter;

import entity.Movie;

import java.util.List;

public interface WithoutFilterDataAccessInterface {
    List<Movie> getRecommendedMovies(String keyword);
//    List<Movie> searchMoviesByKeyword(String keyword);
//    void addToSearchHistory(String userId, String keyword)
}
