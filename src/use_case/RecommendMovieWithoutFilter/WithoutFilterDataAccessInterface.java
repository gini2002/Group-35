package error_cases.RecommendMovieWithoutFilter;

import entity.Movie;

import java.util.List;

public interface WithoutFilterDataAccessInterface {
    List<String> getKeywordsForMovie(int movieId);
//    List<Movie> searchMoviesByKeyword(String keyword);
//    void addToSearchHistory(String userId, String keyword)
}
