package usecase_adaptor.RecommendMovieWithoutFilter;

import entity.Movie;
import view.WithoutFilterResultView;

import java.util.List;

/**
 * Represents the state for displaying results of the "Recommend Movie Without Filter" use case.
 * This class holds the data necessary for presenting the outcome of the movie recommendation process,
 * including the list of recommended movies and any error messages that might occur during the process.
 */
public class WithoutFilterResultState {

    private String error;

    private List<Movie> withoutFilterMovies;

    /**
     * Constructs a new instance of WithoutFilterResultState.
     * Initializes the state with default values for the list of movies and error messages.
     */
    public WithoutFilterResultState() {
    }

    /**
     * Sets the list of movies recommended without filters.
     * This method updates the state with a new list of movies.
     *
     * @param movies The list of movies recommended by the use case.
     */
    public void setWithoutFilterMovies(List<Movie> movies) {
        this.withoutFilterMovies = movies;
    }

    /**
     * Sets an error message in the state.
     * This method is used to update the state with an error message when the recommendation process fails.
     *
     * @param error The error message to be stored in the state.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Retrieves the list of movies recommended without filters.
     * This method is used to access the recommended movies for presentation or further processing.
     *
     * @return A list of movies recommended by the use case.
     */
    public List<Movie> getWithoutFilterMovies() {
        return withoutFilterMovies;
    }

    /**
     * Retrieves the error message stored in the state.
     * This method is used to access the error message for presentation or logging purposes.
     *
     * @return The error message stored in the state, if any.
     */
    public String getError() {
        return error;
    }
}
