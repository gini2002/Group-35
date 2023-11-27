package use_case.MovieSearchByKeyword;

/**
 * The RecommendInputBoundary interface defines the input boundary for the recommendation use case.
 * It declares the method for executing the recommendation based on the provided input data.
 */
public interface RecommendInputBoundary {
    /**
     * Executes the recommendation based on the provided input data.
     *
     * @param recommendInputData The input data for the recommendation use case.
     */
    void execute(RecommendInputData recommendInputData);
}
