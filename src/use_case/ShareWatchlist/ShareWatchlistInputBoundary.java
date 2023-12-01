package use_case.ShareWatchlist;

public interface ShareWatchlistInputBoundary {

    /**
     *
     * @param inputData store the userName of sender of receiver.
     */
    void execute(ShareWatchlistInputData inputData);
}
