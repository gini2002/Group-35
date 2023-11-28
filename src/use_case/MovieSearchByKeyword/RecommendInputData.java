package use_case.MovieSearchByKeyword;

/**
 * The RecommendInputData class represents the input data for the recommendation use case.
 * It encapsulates the keyword used for searching and generating movie recommendations.
 */
public class RecommendInputData {
    private final String keyword;

    /**
     * Constructs a RecommendInputData object with the specified keyword.
     *
     * @param keyword The keyword used for searching and generating recommendations.
     */
    public RecommendInputData(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Retrieves the keyword used for searching and generating recommendations.
     *
     * @return The keyword used for the recommendation use case.
     */
    public String getKeyword() {
        return keyword;
    }
}
