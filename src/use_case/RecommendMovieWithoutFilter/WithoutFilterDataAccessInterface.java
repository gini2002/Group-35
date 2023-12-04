package use_case.RecommendMovieWithoutFilter;

import entity.Movie;

import java.util.List;

/**
 * Interface for data access operations related to movies without applying filters.
 * This interface defines the methods required to retrieve information about movies,
 * specifically focused on keyword-based operations.
 */
public interface WithoutFilterDataAccessInterface {

    /**
     * Retrieves a list of keywords associated with a given movie.
     * This method is used to fetch keywords that are relevant to the movie's content,
     * themes, or other characteristics.
     *
     * @param movieId The unique identifier of the movie for which keywords are being fetched.
     * @return A list of strings representing keywords associated with the movie.
     */
    List<String> getKeywordsForMovie(int movieId);
}
