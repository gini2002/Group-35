package use_case.GetWatchList;

import entity.Movie;

import java.util.List;

public class GetWatchListOutputData {
    private final List<Movie> watchlist;

    public GetWatchListOutputData(List<Movie> watchlist) {
        this.watchlist = watchlist;
    }

    public List<Movie> getWatchlist(){
        return watchlist;
    }
}
