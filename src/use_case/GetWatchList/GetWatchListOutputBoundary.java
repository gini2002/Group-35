package use_case.GetWatchList;
/**
 * The GetWatchListOutputBoundary interface defines the output boundary for get watchlist use case.
 * It declares methods for preparing success and failure views based on the output data.
 */
public interface GetWatchListOutputBoundary {
    /**
     * Prepares a success view based on the provided output data.
     * @param getWatchListOutputData The output data containing information for the success view.
     */
    void getWatchlistSuccessView(GetWatchListOutputData getWatchListOutputData);
    /**
     * Prepares a fail view based on the error message provided.
     * @param error The error message to be included in the fail view.
     */
    void getWatchlistFailView(String error);
}
