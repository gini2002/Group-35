package usecase_adaptor.MovieSearchByKeyword;

import use_case.MovieSearchByKeyword.RecommendInputData;
import use_case.MovieSearchByKeyword.RecommendInputBoundary;

/**
 * The SearchByNameController class serves as a controller for initiating the movie search by keyword use case.
 * It delegates the execution to the corresponding interactor (use case).
 */
public class SearchByNameController {
    /** The interactor responsible for executing the movie search by keyword use case. */
    private final RecommendInputBoundary searchByNameInteractor;

    /**
     * Constructs a SearchByNameController with the provided interactor.
     *
     * @param searchByNameInteractor The interactor for executing the movie search by keyword use case.
     */
    public SearchByNameController(RecommendInputBoundary searchByNameInteractor) {
        this.searchByNameInteractor = searchByNameInteractor;
    }

    /**
     * Initiates the execution of the movie search by keyword use case with the given keyword.
     *
     * @param keyword The keyword used for searching and generating movie recommendations.
     */
    public void execute(String keyword) {
        RecommendInputData recommendInputData = new RecommendInputData(keyword);
        searchByNameInteractor.execute(recommendInputData);
    }
}
