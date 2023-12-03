package usecase_adaptor.GetWatchlist;

import java.util.List;

public class GetWatchListState {
    private List<String> watchlistNames;
    private List<String> watchlistPosters;
    private String getWatchListError;

    private String loggedinusername;
    private List<Integer> ids;


    public GetWatchListState(GetWatchListState copy){
        this.getWatchListError = copy.getWatchListError;
        this.watchlistNames = copy.watchlistNames;
        this.watchlistPosters = copy.watchlistPosters;
        this.ids = copy.ids;
        this.loggedinusername = copy.getLoggedinusername();
    }
    public GetWatchListState(){}

    public List<String> getWatchlistNames() {
        return watchlistNames;
    }

    public void setLoggedinusername(String loggedinusername) {
        this.loggedinusername = loggedinusername;
    }

    public String getLoggedinusername() {
        return loggedinusername;
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

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

}
