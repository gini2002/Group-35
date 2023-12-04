package usecase_adaptor.RecommendMovieWithoutFilter;

import entity.Movie;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameState;
import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for managing the presentation state of the "Recommend Movie Without Filter" feature.
 * This class holds the data necessary for presenting movie recommendations and handling user input.
 * It supports property change notifications to facilitate communication with the view component.
 */
public class WithoutFilterViewModel extends ViewModel {

    public final String TITLE_LABEL = "Movie Recommendation View Without Filter";
    public final String USERNAME_LABEL = "Enter your username here";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String MAIN_MENU_BUTTON_LABEL = "Back to Main Menu";

    private String usernameInput = "";
    private List<Movie> withoutFilterMovies;

    private WithoutFilterState withoutFilterState = new WithoutFilterState();


    private String error = null;

    /**
     * Constructs a new instance of WithoutFilterViewModel.
     * Initializes the view model with a specific title for the user interface.
     */

    public WithoutFilterViewModel() {
        super("movie_recommendation_filter");
    }


    /**
     * Retrieves the titles of recommended movies.
     * This method fetches the names of movies recommended without filters.
     *
     * @return An array of movie titles recommended by the use case.
     */
    public String[] getRecommendedMovies() {
        List<String> movies = new ArrayList<>();
        if (this.withoutFilterMovies != null) {
            for (Movie movie : this.withoutFilterMovies) {
                movies.add(movie.getName());
            }
        }
        System.out.println("Called in result view model: " + movies);
        return movies.toArray(new String[0]);
    }

    /**
     * Retrieves the unique identifier for a movie given its title.
     * This method finds the ID of a movie based on its name, typically for further operations.
     *
     * @param movieTitle The title of the movie for which the ID is required.
     * @return The ID of the movie if found, or 0 if no matching movie is found.
     */

    public int getID(String movieTitle) {
        for (Movie movie : withoutFilterMovies) {
            if (movie.getName().equals(movieTitle)) {
                return movie.getID();
            }
        }
        return 0;
    }

    /**
     * Sets the list of movies recommended without filters.
     * This method updates the state with a new list of movies.
     *
     * @param withoutFilterMovies The list of movies recommended by the use case.
     */
    public void setWithoutFilterMovies(List<Movie> withoutFilterMovies) {
        this.withoutFilterMovies = withoutFilterMovies;
    }

    /**
     * Sets the error message in the state.
     * This method is used to record an error message in the view model.
     *
     * @param error The error message to be stored in the view model.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Retrieves the error message stored in the state.
     * This method accesses the error message for presentation or logging purposes.
     *
     * @return The error message stored in the state, if any.
     */
    public String getError() {
        return error;
    }

    /**
     * Retrieves the current state of the movie recommendation process.
     * This state includes recommended movies, user input, and error messages.
     *
     * @return The current state of the movie recommendation process.
     */
    public WithoutFilterState getState() {return this.withoutFilterState;}

    /**
     * Updates the state of the movie recommendation process.
     * This method sets a new state for the recommendation process.
     *
     * @param withoutFilterState The new state to be set for the movie recommendation process.
     */
    public void setState(WithoutFilterState withoutFilterState) {
        this.withoutFilterState = withoutFilterState;
    }



    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies all registered listeners about a change in the username input.
     * This method updates the UI when the username input changes.
     */

    public void firePropertyChanged() {
        support.firePropertyChange("usernameInput", null, this.usernameInput);
    }

    /**
     * Notifies all registered listeners about a change in the list of recommended movies.
     * This method updates the UI when the list of recommended movies changes.
     */
    public void fireRecommendedMoviesChanged() {
        support.firePropertyChange("withoutFilterMovies", null, usernameInput);
    }

    /**
     * Adds a PropertyChangeListener to this ViewModel.
     * Listeners are notified of changes to the state of the movie recommendation process.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
