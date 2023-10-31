package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    private SearchHistory searchHistory = new SearchHistory();
    @Override
    public List<Movie> searchMovies(String keyword) {
        // Perform an API call to get recommended movies based on the keyword.
        // Simulate the API call here.
        List<Movie> recommendedMovies = new ArrayList<>();

        // Add the retrieved movies to the search history
        for (Movie movie: recommendedMovies) {
            searchHistory.addToSearchHistory(movie);
        }

        return recommendedMovies;
    }

    @Override
    public List<Movie> getSearchHistory() {
        return searchHistory.getSearchHistory();
    }
}

