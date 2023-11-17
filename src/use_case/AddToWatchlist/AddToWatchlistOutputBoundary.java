package use_case.AddToWatchlist;

public interface AddToWatchlistOutputBoundary {
    void PrepareFailView(String error);

    void PrepareSuccessView(AddToWatchlistOutputData outputData);
}
