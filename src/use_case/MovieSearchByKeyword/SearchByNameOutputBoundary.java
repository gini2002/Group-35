package use_case.MovieSearchByKeyword;

/**
 * The SearchByNameOutputBoundary interface defines the output boundary for the movie search by keyword use case.
 * It declares methods for preparing success and failure views based on the output data.
 */
public interface SearchByNameOutputBoundary {
    /**
     * Prepares a success view based on the provided output data.
     *
     * @param outputData The output data containing information for the success view.
     */
    void prepareSuccessView(RecommendOutputData outputData);

    /**
     * Prepares a fail view with the given error message.
     *
     * @param error The error message to be included in the fail view.
     */
    void prepareFailView(String error);
}
