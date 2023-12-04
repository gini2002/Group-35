package use_case.RecommendMovieWithoutFilter;

/**
 * This interface represents the input boundary for the "Recommend Movie Without Filter" use case.
 * It defines the contract for executing the recommendation process without applying any filters.
 * This interface is typically implemented by the interactor that handles the core logic of the use case.
 */
public interface WithoutFilterInputBoundary {
    /**
     * Executes the recommendation process based on the provided input data.
     * This method is responsible for initiating the process of recommending movies without applying filters.
     * The input data includes any necessary information required to perform the recommendation.
     *
     * @param withoutFilterInputData The input data required to execute the movie recommendation process.
     */
    void execute(WithoutFilterInputData withoutFilterInputData);
}
