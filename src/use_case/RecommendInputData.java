package use_case;
import entity.Movie;  // Import the Movie class from the entity package.
import java.util.List;


public class RecommendInputData {
    private final String keyword;
    private final List<Movie> searchHistory;

    public RecommendInputData(String keyword, List<Movie> searchHistory) {
        this.keyword = keyword;
        this.searchHistory = searchHistory;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<Movie> getSearchHistory() {
        return searchHistory;
    }
}
