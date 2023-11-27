package usecase_adaptor.SearchList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import entity.Movie;
import usecase_adaptor.ViewModel;

/**
 * The SearchListViewModel class represents the view model for displaying a search list of movies.
 * It provides methods to access and manipulate the search list and error information.
 */
public class SearchListViewModel extends ViewModel {
    /** The label for the search list view. */
    public final String TITLE_LABEL = "Search list view";

    /** The label for the main menu button. */
    public static final String MAIN_MENU_LABEL = "Back to Main Menu";

    /** The list of movies in the search list. */
    private List<Movie> searchList;

    /** The error message, if any. */
    private String error;

    /**
     * Constructs a SearchListViewModel with the specified view name.
     */
    public SearchListViewModel() {
        super("movie_recommendation");
    }

    /** The property change support for handling property change events. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Retrieves an array of movie names in the search list.
     *
     * @return An array of movie names in the search list.
     */
    public String[] getSearchList() {
        List<String> movies = new ArrayList<>();
        if (this.searchList != null) {
            for (Movie movie : this.searchList) {
                movies.add(movie.getName());
            }
        }
        return movies.toArray(new String[0]);
    }

    /**
     * Sets the search list based on the provided list of recommended movies.
     *
     * @param recommendedMovies The list of recommended movies to be added to the search list.
     */
    public void setSearchList(List<Movie> recommendedMovies) {
        if (searchList == null) {
            searchList = new ArrayList<>();
        }
        searchList.addAll(recommendedMovies);
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
     * Fires a property change event for the search list.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("searchList", null, this.searchList);
    }
}
