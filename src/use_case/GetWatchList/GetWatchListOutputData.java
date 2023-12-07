package use_case.GetWatchList;

import java.util.List;
/**
 * The GetWatchListOutputData class represents the output data for the get watchlist use case.
 * It includes a list of movie names, a list of poster path and a list of movie ids as well as the name of user that
 * is currently logged in.
 */
public class GetWatchListOutputData {
     /** The names of movies in the watchlist. */
    private final List<String> names;
    /** The poster path of movies in the watchlist. */
    private final List<String> poster_urls;
    /** The ids of movies in the watchlist. */
    private final List<Integer> ids;
    /** The name of user that is currently logged in. */
    private final String logged_in_username;

    /**
     * Constructs a GetWatchListOutputData object with a list of movie names, a list of poster path and a list of
     * movie ids as well as the name of user that is currently logged in.
     *
     * @param ids The ids of movies in the watchlist.
     * @param names The names of movies in the watchlist.
     * @param poster_urls The poster path of movies in the watchlist.
     * @param name The name of user that is currently logged in.
     */
    public GetWatchListOutputData(List<String> names, List<String> poster_urls, List<Integer> ids, String name) {
        this.names = names;
        this.poster_urls = poster_urls;
        this.ids = ids;
        this.logged_in_username = name;
    }
    /**
     * Retrieves the titles of the movies in the watchlist of a certain user.
     *
     * @return The titles of the movies in the watchlist.
     */
    public List<String> getNames() {
        return names;
    }
    /**
     * Retrieves the poster paths of the movies in the watchlist of a certain user.
     *
     * @return The poster path of the movies in the watchlist.
     */
    public List<String> getPoster_urls() {
        return poster_urls;
    }
    /**
     * Retrieves the ids of the movie in the watchlist of a certain user.
     *
     * @return The ids of the movies in the watchlist.
     */
    public List<Integer> getIds(){return ids;}
    /**
     * Retrieves the name of user that is currently logged in.
     *
     * @return The name of user that is currently logged in.
     */
    public String getLogged_in_username() {
        return logged_in_username;
    }
}
