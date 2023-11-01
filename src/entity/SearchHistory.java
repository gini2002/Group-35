package entity;

import java.util.ArrayList;
import java.util.List;

class SearchHistory {
    private List<Movie> searchHistory = new ArrayList<>();

    public void addToSearchHistory(Movie movie) {
        searchHistory.add(movie);
    }

    public List<Movie> getSearchHistory() {
        return searchHistory;
    }
}

