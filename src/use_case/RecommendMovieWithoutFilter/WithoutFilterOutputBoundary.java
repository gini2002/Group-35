package use_case.RecommendMovieWithoutFilter;

/**
 * Interface for the output boundary of the "Recommend Movie Without Filter" use case.
 * This interface defines the contract for presenting the results of the movie recommendation process.
 * Implementations of this interface will handle the success and failure scenarios of the recommendation process.
 */
public interface WithoutFilterOutputBoundary {

    /**
     * Handles the successful view presentation of recommended movies.
     * This method is called when the movie recommendation process completes successfully
     * and is responsible for presenting the successful outcome and recommended movies to the user.
     *
     * @param outputData The data related to the successful movie recommendations, including a list of recommended movies.
     */
    void WithoutFilterSuccessView(WithoutFilterOutputData outputData);

    /**
     * Handles the failure view presentation when the recommendation process fails.
     * This method is invoked in scenarios where the recommendation process encounters an error
     * and is responsible for presenting the error message to the user.
     *
     * @param error The error message to be displayed in case of a failure in the recommendation process.
     */

    void WithoutFilterFailView(String error);
}
