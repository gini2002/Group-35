package use_case.GetWatchList;

import use_case.GetDetailMovie.GetDetailMovieOutputData;

public interface GetWatchListOutputBoundary {
    void getWatchlistSuccessView(GetWatchListOutputData getWatchListOutputData);
    void getWatchlistFailView(String error);
}
