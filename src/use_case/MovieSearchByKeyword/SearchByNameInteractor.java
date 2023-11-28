package use_case.MovieSearchByKeyword;

import entity.Movie;

import java.util.List;

/**
 * The SearchByNameInteractor class represents the interactor for the movie search by keyword use case.
 * It implements the RecommendInputBoundary interface and is responsible for executing the use case logic.
 */
public class SearchByNameInteractor implements RecommendInputBoundary {
    private final SearchByNameDataAccessInterface searchByNameDataAccessObject;
    private final SearchByNameOutputBoundary searchByNamePresenter;

    /**
     * Constructs a SearchByNameInteractor with the provided data access object and presenter.
     *
     * @param searchByNameDataAccessObject The data access object for retrieving recommended movies.
     * @param searchByNamePresenter       The presenter for handling the output of the use case.
     */
    public SearchByNameInteractor(SearchByNameDataAccessInterface searchByNameDataAccessObject,
                                  SearchByNameOutputBoundary searchByNamePresenter) {
        this.searchByNameDataAccessObject = searchByNameDataAccessObject;
        this.searchByNamePresenter = searchByNamePresenter;
    }

    /**
     * Executes the movie search by keyword use case based on the provided input data.
     *
     * @param recommendInputData The input data for the recommendation use case.
     */
    @Override
    public void execute(RecommendInputData recommendInputData) {
        String keyword = recommendInputData.getKeyword();

        // Check if the keyword is not null or empty
        if (keyword == null || keyword.isEmpty()) {
            searchByNamePresenter.prepareFailView("Invalid keyword");
            return;
        }

        // Retrieve recommended movies based on the keyword from the data access
        List<Movie> recommendedMovies = searchByNameDataAccessObject.getRecommendedMovies(keyword);

        if (recommendedMovies.isEmpty()) {
            searchByNamePresenter.prepareFailView("No movies found for the keyword: " + keyword);
        } else {
            // Prepare a success view with the list of recommended movies
            RecommendOutputData outputData = new RecommendOutputData(recommendedMovies);
            searchByNamePresenter.prepareSuccessView(outputData);
        }
    }
}
