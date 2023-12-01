package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The SearchHistory class represents the search history of a user, storing a list of movies that the user has searched for.
 */
public class SearchHistory {
    private List<Movie> searchHistory = new ArrayList<>();

    /**
     * Constructs an empty SearchHistory.
     */
    public SearchHistory() {}

    /**
     * Constructs a SearchHistory with the given list of movies.
     *
     * @param movies The list of movies to initialize the search history.
     */
    public SearchHistory(List<Movie> movies) {
        searchHistory = movies;
    }

    /**
     * Adds a movie to the search history.
     *
     * @param movie The movie to be added to the search history.
     */
    public void addToSearchHistory(Movie movie) {
        searchHistory.add(movie);
    }

    /**
     * Retrieves the search history as a list of movies.
     *
     * @return The list of movies in the search history.
     */
    public List<Movie> getSearchHistory() {
        return searchHistory;
    }

    @Override
    public String toString() {
        String result = "";

        if (searchHistory == null) {
            return "";
        } else if (searchHistory.isEmpty()) {
            return "";
        } else {
            for (Movie movie:searchHistory) {
                int id = movie.getID();
                result = result + id + "#";
            }
        return result.substring(0, result.length()-1);
        }
    }
}
