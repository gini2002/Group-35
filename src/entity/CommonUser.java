package entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

/**
 * CommonUser class represents a user in the system with common functionalities.
 */
class CommonUser implements User {

    private int id;
    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private SearchHistory searchHistory = new SearchHistory();

    private Watchlist watchlist = new Watchlist();

    private Map<String, Watchlist> sharedWatchlist = new HashMap<>();

    /**
     * Constructs a CommonUser with the specified name, password, creation time, search history, and watchlist.
     *
     * @param name           The name of the user.
     * @param password       The password associated with the user.
     * @param creationTime   The timestamp representing the user's creation time.
     * @param searchHistory  The search history associated with the user.
     * @param watchlist      The watchlist associated with the user.
     */
    CommonUser(String name, String password, LocalDateTime creationTime, SearchHistory searchHistory, Watchlist watchlist) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.searchHistory = searchHistory;
        this.watchlist = watchlist;
    }

    /**
     *
     * @return int id of the user.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the timestamp representing the user's creation time.
     *
     * @return The creation time of the user.
     */
    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * Retrieves the search history associated with the user.
     *
     * @return The list of movies in the user's search history.
     */
    @Override
    public List<Movie> getSearchHistory() {
        return searchHistory.getSearchHistory();
    }

    /**
     * Retrieves the watchlist associated with the user.
     *
     * @return The list of movies in the user's watchlist.
     */
    @Override
    public List<Movie> getWatchlist() {
        return watchlist.getWatchlist();
    }

    /**
     * Sets the shared watchlist for a specified user.
     *
     * @param userName   The name of the user with whom the watchlist is shared.
     * @return List of movies shared by the user with userName.
     */
    @Override
    public List<Movie> getSharedWatchlist(String userName) {
        return sharedWatchlist.get(userName).getWatchlist();
    }

    @Override
    public Map<String, Watchlist> getSharedWatchlist() {
        return this.sharedWatchlist;
    }

    /**
     * save an id in user.
     * @param id int id of user.
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }


    /**
     *
     * @param userName  the name of user who share the watchlist with logged in user.
     * @param watchlist watchlist of user who has userName.
     */

    @Override
    public void setSharedWatchlist(String userName, Watchlist watchlist) {
        sharedWatchlist.put(userName, watchlist);
    }

    @Override
    public void setCompleteSharedWatchlist(Map<String, Watchlist> sharedWatchlist) {
        this.sharedWatchlist = sharedWatchlist;
    }

    /**
     * Adds a movie to the user's watchlist.
     *
     * @param movie The movie to be added to the watchlist.
     */
    @Override
    public void addMovieToWatchlist(Movie movie) {
        watchlist.addMovie(movie);
    }
    public void deleteMovieToWatchlist(Movie movie) {
        watchlist.deleteMovie(movie);
    }
}

