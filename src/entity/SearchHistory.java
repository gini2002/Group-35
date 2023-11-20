package entity;

import java.util.ArrayList;
import java.util.List;

public class SearchHistory {
    private List<Movie> searchHistory = new ArrayList<>();

    public SearchHistory() {}

    public SearchHistory(List<Movie> movies) {
        searchHistory = movies;
    }

    public void addToSearchHistory(Movie movie) {
        searchHistory.add(movie);
    }

    public List<Movie> getSearchHistory() {
        return searchHistory;
    }
}

