package use_case.DeleteWatchlist;

public interface DeleteWatchlistOutputBoundary {

    void PrepareFailView(String error);

    /**
     *
     * @param outputData that involve the movie being deleted.
     */
    void PrepareSuccessView(DeleteWatchlistOutputData outputData);
}
