package use_case.GetWatchList;

public interface GetWatchListOutputBoundary {
    void getWatchlistSuccessView(GetWatchListOutputData getWatchListOutputData);
    void getWatchlistFailView(String error);
}
