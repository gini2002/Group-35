package error_cases.RecommendMovieWithoutFilter;


public class WithoutFilterInputData {
    private final String watchlistId; // Identifier for the user's watchlist, if used separately

    // Additional fields can be added as needed, for example, user preferences

    // Constructor
    public WithoutFilterInputData(String watchlistId) {
        this.watchlistId = watchlistId;
    }

    // Getters for the fields

    public String getWatchlistId() {
        return watchlistId;
    }
}