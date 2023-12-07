package usecase_adaptor.GetWatchlist;

import java.util.List;
/**
 * Represents the state for the get watchlist for certain user use case,
 * including the list of movies in the watchlist of certain user and the username of the user.
 */
public class GetWatchListState {
    /** The names of the movies in the watchlist of certain user. */
    private List<String> watchlistNames;
    /** The poster paths of the movies in the watchlist of certain user. */
    private List<String> watchlistPosters;
    /** The error message when there is an error occur. */
    private String getWatchListError;
    /** The username of user that is logged in currently */
    private String loggedinusername;
    /** The ids of the movies in the watchlist of certain user. */
    private List<Integer> ids;

    /**
     * Constructs a new instance of GetWatchListState with the watchlistNames, watchlistPosters,
     * getWatchListError, ids and loggedinusername of the input state.
     * @param copy The state with old values of watchlistNames, watchlistPosters, getWatchListError, ids and loggedinusername.
     */
    public GetWatchListState(GetWatchListState copy){
        this.getWatchListError = copy.getWatchListError;
        this.watchlistNames = copy.watchlistNames;
        this.watchlistPosters = copy.watchlistPosters;
        this.ids = copy.ids;
        this.loggedinusername = copy.getLoggedinusername();
    }
    /**
     * Constructs a new instance of GetWatchListState.
     * Initializes the state with default values for watchlistNames, watchlistPosters, getWatchListError, ids and loggedinusername.
     */
    public GetWatchListState(){}
    /**
     * Retrieves the titles of the movies in the watchlist of certain user.
     * @return The titles of the movies in the watchlist of certain user.
     */
    public List<String> getWatchlistNames() {
        return watchlistNames;
    }
    /**
     * Sets the name of the user that is currently logged in.
     * @param loggedinusername The name of the user that is currently logged in.
     */
    public void setLoggedinusername(String loggedinusername) {
        this.loggedinusername = loggedinusername;
    }
    /**
     * Retrieves the name of user that is currently logged in.
     * @return the name of user that is currently logged in.
     */
    public String getLoggedinusername() {
        return loggedinusername;
    }

    /**
     * Sets te titles of the movies in the watchlist of certain user.
     * @param watchlistnames The titles of the movies in the watchlist of certain user.
     */
    public void setWatchlistNames(List<String> watchlistnames) {
        this.watchlistNames = watchlistnames;
    }
    /**
     * Retrieves the poster paths of the movies in the watchlist of certain user.
     * @return The poster paths of the movies in the watchlist of certain user.
     */
    public List<String> getPosters() {
        return watchlistPosters;
    }
    /**
     * Sets the poster paths of the movies in the watchlist of certain user.
     * @param watchlistPosters The poster paths of the movies in the watchlist of certain user.
     */
    public void setWatchlistPosters(List<String> watchlistPosters) {
        this.watchlistPosters = watchlistPosters;
    }
    /**
     * Sets the error message when there is an error occur.
     * @param error The error message when there is an error occur.
     */
    public String setGetWatchListError(String error) {
        return this.getWatchListError = error;
    }
    /**
     * Retrieves the error message when there is an error occur.
     * @return error The error message when there is an error occur.
     */
    public String getGetWatchListError() {
        return getWatchListError;
    }
    /**
     * Retrieves the ids of the movies in the watchlist of certain user.
     * @return The ids of the movies in the watchlist of certain user.
     */
    public List<Integer> getIds() {
        return ids;
    }
    /**
     * Sets the ids of the movies in the watchlist of certain user.
     * @param ids The ids of the movies in the watchlist of certain user.
     */
    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

}
