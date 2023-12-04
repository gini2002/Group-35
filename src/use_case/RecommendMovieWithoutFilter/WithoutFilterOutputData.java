package use_case.RecommendMovieWithoutFilter;

import entity.Movie;

import java.util.List;

/**
 * This class represents the output data for the "Recommend Movie Without Filter" use case.
 * It encapsulates a list of movies recommended without applying any filters.
 * This class is used to transfer the recommended movies data from the interactor to the presenter.
 */
public class WithoutFilterOutputData {

    private final List<Movie> withoutFilterMovies;
    private boolean useCaseFailed;

    /**
     * Constructs a new instance of WithoutFilterOutputData with a list of movies recommended without filters.
     * This constructor initializes the class with the provided list of movies.
     *
     * @param withoutFilterMovies The list of movies recommended by the use case.
     */
    public WithoutFilterOutputData(List<Movie> withoutFilterMovies) {
        this.withoutFilterMovies = withoutFilterMovies;
    }


    /**
     * Retrieves the list of movies recommended without applying filters.
     * This method is used to access the recommended movies for presentation or further processing.
     *
     * @return A list of movies recommended by the use case.
     */
    public List<Movie> getWithoutFilterMovies() {
        System.out.println(withoutFilterMovies);
        return withoutFilterMovies;
    }

}
