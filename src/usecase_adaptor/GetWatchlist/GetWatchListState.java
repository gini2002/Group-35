package usecase_adaptor.GetWatchlist;

import entity.Movie;

import java.util.List;

public class GetWatchListState {
    private List<String> watchlistNames;
    private List<String> watchlistPosters;
    private String getWatchListError;


    public GetWatchListState(GetWatchListState copy){
        this.getWatchListError = copy.getWatchListError;
        this.watchlistNames = copy.watchlistNames;
        this.watchlistPosters = copy.watchlistPosters;
    }
    public GetWatchListState(){}

    public List<String> getWatchlistNames() {
        return watchlistNames;
    }

    public void setWatchlistNames(List<String> watchlistnames) {
        this.watchlistNames = watchlistnames;
    }
    public List<String> getPosters() {
        return watchlistPosters;
    }

    public void setWatchlistPosters(List<String> watchlistPosters) {
        this.watchlistPosters = watchlistPosters;
    }

    public String setGetWatchListError(String error) {
        return this.getWatchListError = error;
    }

    public String getGetWatchListError() {
        return getWatchListError;
    }
}
