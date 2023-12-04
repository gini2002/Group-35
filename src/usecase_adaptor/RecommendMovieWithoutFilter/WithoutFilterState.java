package usecase_adaptor.RecommendMovieWithoutFilter;

import entity.Movie;

import java.util.List;

/**
 * Represents the state for the "Recommend Movie Without Filter" use case.
 * This class holds the data necessary for managing the state of movie recommendations,
 * including the list of recommended movies and the username of the user for whom the recommendations are made.
 */
public class WithoutFilterState {

    private List<Movie> movies;
    private String username;

    /**
     * Constructs a new instance of WithoutFilterState.
     * Initializes the state with default values for movies and username.
     */
    public WithoutFilterState() {}

    /**
     * Sets the list of movies recommended without filters.
     * This method updates the state with a new list of movies.
     *
     * @param movies The list of movies recommended by the use case.
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Retrieves the list of movies recommended without filters.
     * This method is used to access the recommended movies for presentation or further processing.
     *
     * @return A list of movies recommended by the use case.
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Sets the username of the user for whom the movies are recommended.
     * This method updates the state with the specified username.
     *
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username of the user for whom the movies are recommended.
     * This method is used to access the username for personalized recommendations or logging purposes.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
}

