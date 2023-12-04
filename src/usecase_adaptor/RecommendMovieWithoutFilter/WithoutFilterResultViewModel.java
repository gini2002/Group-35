package usecase_adaptor.RecommendMovieWithoutFilter;

import entity.Movie;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewModel for managing the presentation state of the "Recommend Movie Without Filter" results.
 * This class handles the user interface logic for displaying recommended movies and any errors.
 * It supports property change notifications to update the view when the state changes.
 */
public class WithoutFilterResultViewModel {
    public final String TITLE_LABEL = "Movie Results View";
    public static final String MAIN_MENU_LABEL = "Back to Main Menu";

    private List<Movie> withoutFilterMovies;


    private String error;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private WithoutFilterResultState state = new WithoutFilterResultState();

    /**
     * Retrieves the titles of recommended movies.
     * This method is used to fetch the names of movies recommended without filters.
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
     * Sets the list of movies recommended without filters.
     * This method updates the state with a new list of movies.
     *
     * @param withoutFilterMovies The list of movies recommended by the use case.
     */
    public void setWithoutFilterMovies(List<Movie> withoutFilterMovies) {
        this.withoutFilterMovies = withoutFilterMovies;
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

//    public void setError(String error) {
//        this.error = error;
//        firePropertyChanged("error");
//    }

    /**
     * Adds a PropertyChangeListener to this ViewModel.
     * Listeners are notified of changes to the state of the movie recommendation process.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Notifies all registered listeners about a change in the state.
     * This method is used to update the UI when the state of the movie recommendation process changes.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("withoutFilterMovies", null, this.state);
    }

    /**
     * Sets an error message in the state and triggers a property change event.
     * This method is used to update the state with an error message when the recommendation process fails.
     *
     * @param error The error message to be stored in the state.
     */
    public void setError(String error) {
        this.state.setError(error);
    }

    /**
     * Retrieves the current state of the movie recommendation process.
     * This state includes any movies or errors related to the process.
     *
     * @return The current state of the movie recommendation process.
     */
    public WithoutFilterResultState getState() {return this.state;}
}
