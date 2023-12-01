package use_case.ShareWatchlist;

public interface ShareWatchlistOutputBoundary {

    /**
     * prepare the view when fail to share.
     * @param error error message of fail view.
     */
    void prepareFailView(String error);

    /**
     * prepare the view when success to share.
     * @param outputData that involve the receiver's userName.
     */
    void prepareSuccessView(ShareWatchlistOutputData outputData);
}
