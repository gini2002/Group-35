package use_case.MovieSearchByKeyword;

import entity.Movie;

import java.util.List;

/**
 * The SearchByNameDataAccessInterface interface defines the data access contract for searching and retrieving recommended movies.
 */
public interface SearchByNameDataAccessInterface {
    /**
     * Retrieves a list of recommended movies based on the provided keyword.
     *
     * @param keyword The keyword used for searching and generating recommendations.
     * @return A list of recommended movies.
     */
    List<Movie> getRecommendedMovies(String keyword);
}
