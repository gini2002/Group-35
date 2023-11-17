package use_case.ShareWatchlist;

public interface ShareWatchlistOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(ShareWatchlistOutputData outputData);
}
