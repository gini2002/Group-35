package use_case.GetWatchList;
/**
 * The GetWatchListInputBoundary interface defines the input boundary for the get watchlist use case.
 * It declares the method for executing the getting watchlist based on the provided input data.
 */
public interface GetWatchListInputBoundary {
    /**
     * Executes and get watchlist of certain user based on the provided input data.
     *
     * @param getWatchListIntputData The input data when executing getting watchlist of certain user.
     */
    void execute(GetWatchListInputData getWatchListIntputData);
}
