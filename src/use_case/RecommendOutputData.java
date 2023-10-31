package use_case;

import entity.Movie;

import java.util.List;

public class RecommendOutputData {

    private final List<Movie> searchHistory;
    private final List<Movie> recommendedMovies;
    private boolean useCaseFailed;

    public RecommendOutputData(List<Movie> searchHistory, List<Movie> recommendedMovies) {
        this.searchHistory = searchHistory;
        this.recommendedMovies = recommendedMovies;
    }

    public List<Movie> getSearchHistory() {
        return searchHistory;
    }

    public List<Movie> getRecommendedMovies() {
        return recommendedMovies;
    }

}
