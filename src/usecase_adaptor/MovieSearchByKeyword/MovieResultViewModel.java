package usecase_adaptor.MovieSearchByKeyword;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import entity.Movie;

/**
 * The MovieResultViewModel class represents the view model for displaying movie search results.
 * It provides methods to access recommended movies and handles property change events for data binding.
 */
public class MovieResultViewModel {
    /** The label for the movie results view. */
    public final String TITLE_LABEL = "Movie Results View";

    /** The label for the main menu button. */
    public static final String MAIN_MENU_LABEL = "Back to Main Menu";

    /** The list of recommended movies. */
    private List<Movie> recommendedMovies;

    /** The error message, if any. */
    private String error;

    /** The property change support for handling property change events. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Retrieves an array of recommended movie names.
     *
     * @return An array of recommended movie names.
     */
    public String[] getRecommendedMovies() {
        List<String> movies = new ArrayList<>();
        if (this.recommendedMovies != null) {
            for (Movie movie : this.recommendedMovies) {
                movies.add(movie.getName());
            }
        }
        System.out.println("Called in result view model: " + movies);
        return movies.toArray(new String[0]);
    }

    /**
     * Sets the list of recommended movies.
     *
     * @param recommendedMovies The list of recommended movies.
     */
    public void setRecommendedMovies(List<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }

    /**
     * Retrieves the error message, if any.
     *
     * @return The error message.
     */
    public String getError() {
        return error;
    }

    /**
     * Adds a property change listener.
     *
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Fires a property change event for recommended movies.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("recommendedMovies", null, this.recommendedMovies);
    }
}
