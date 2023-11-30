package use_case.AddToWatchlist;

public interface AddToWatchlistOutputBoundary {

    /**
     *
     * @param error string of error message when add failed.
     */
    void PrepareFailView(String error);

    /**
     *
     * @param outputData that involve the movie being added.
     */
    void PrepareSuccessView(AddToWatchlistOutputData outputData);
}
