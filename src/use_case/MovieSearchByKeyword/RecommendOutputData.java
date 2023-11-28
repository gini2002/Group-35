package use_case.MovieSearchByKeyword;

import entity.Movie;

import java.util.List;

/**
 * The RecommendOutputData class represents the output data for the recommendation use case.
 * It includes the search history, recommended movies, and a flag indicating whether the use case failed.
 */
public class RecommendOutputData {

    private final List<Movie> searchHistory;
    private final List<Movie> recommendedMovies;
    private boolean useCaseFailed;

    /**
     * Constructs a RecommendOutputData object with the specified recommended movies.
     *
     * @param recommendedMovies The list of recommended movies.
     */
    public RecommendOutputData(List<Movie> recommendedMovies) {
        this.searchHistory = recommendedMovies;
        this.recommendedMovies = recommendedMovies;
    }

    /**
     * Retrieves the search history associated with the recommendation output.
     *
     * @return The list of movies in the search history.
     */
    public List<Movie> getSearchHistory() {
        System.out.println("Search History: " + searchHistory);
        return searchHistory;
    }

    /**
     * Retrieves the list of recommended movies.
     *
     * @return The list of recommended movies.
     */
    public List<Movie> getRecommendedMovies() {
        return recommendedMovies;
    }

}
