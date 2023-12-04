package usecase_adaptor.RecommendMovieWithoutFilter;

import use_case.RecommendMovieWithoutFilter.WithoutFilterInputBoundary;
import use_case.RecommendMovieWithoutFilter.WithoutFilterInputData;

/**
 * Controller for handling requests related to the "Recommend Movie Without Filter" use case.
 * This class acts as an intermediary between the user interface and the application's business logic,
 * specifically for recommending movies without applying any filters.
 */
public class WithoutFilterController {
    final WithoutFilterInputBoundary withoutFilterInteractor;

    /**
     * Constructs a new WithoutFilterController with a specified WithoutFilterInputBoundary.
     * This constructor allows the controller to interact with the business logic
     * for recommending movies without filters.
     *
     * @param withoutFilterInteractor The input boundary to interact with the movie recommendation logic.
     */

    public WithoutFilterController(WithoutFilterInputBoundary withoutFilterInteractor){
        this.withoutFilterInteractor = withoutFilterInteractor;
    }

    /**
     * Executes the movie recommendation process without filters for a given user.
     * It takes the username, creates an input data object, and passes it to the interactor for processing.
     *
     * @param username The username of the user for whom movies are to be recommended.
     */

    public void execute(String username){
        WithoutFilterInputData withoutFilterInputData = new WithoutFilterInputData(username);
        withoutFilterInteractor.execute(withoutFilterInputData);
    }
}
