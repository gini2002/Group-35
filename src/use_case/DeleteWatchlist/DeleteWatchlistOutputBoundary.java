package use_case.DeleteWatchlist;

public interface DeleteWatchlistOutputBoundary {

    void PrepareFailView(String error);

    /**
     *
     * @param outputData that involve the movie being added.
     */
    void PrepareSuccessView(DeleteWatchlistOutputData outputData);
}
