package usecase_adaptor.MovieSearchByKeyword;

import entity.Movie;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieState;
import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * The SearchByNameViewModel class represents the view model for the movie search by keyword use case.
 * It provides methods to access and manipulate the keyword input, recommended movies, and error information.
 */
public class SearchByNameViewModel extends ViewModel {

    /** The label for the movie recommendation view. */
    public final String TITLE_LABEL = "Movie Recommendation View";

    /** The label for the keyword input field. */
    public final String KEYWORD_LABEL = "Enter your keyword here";

    /** The label for the search button. */
    public static final String SEARCH_BUTTON_LABEL = "Search";

    /** The label for the view search list button. */
    public static final String SEARCH_LIST_BUTTON_LABEL = "View Searchlist";
    public static final String MAIN_MENU_BUTTON_LABEL = "Back to Main Menu";

    /** The input for the keyword search. */
    private String keywordInput = "";

    /** The list of recommended movies. */
    private List<Movie> recommendedMovies;

    /** The error message, if any. */
    private String error = null;
    private String username;
    private SearchByNameState searchByNameState = new SearchByNameState();

    /**
     * Constructs a SearchByNameViewModel with the specified view name.
     */
    public SearchByNameViewModel() {
        super("movie_recommendation");
    }

    /**
     * Sets the keyword input value.
     *
     * @param keywordInput The keyword input value.
     */
    public void setKeywordInput(String keywordInput) {
        this.keywordInput = keywordInput;
        firePropertyChanged();
    }

    /**
     * Retrieves the current keyword input value.
     *
     * @return The current keyword input value.
     */
    public String getKeywordInput() {
        return keywordInput;
    }

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

    public int getID(String movieTitle) {
        for (Movie movie : recommendedMovies) {
            if (movie.getName().equals(movieTitle)) {
                return movie.getID();
            }
        }
        return 0;
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
     * Sets the error message.
     *
     * @param error The error message.
     */


    public void setError(String error) {
        this.error = error;
    }

    /**
     * Retrieves the error message, if any.
     *
     * @return The error message.
     */
    public String getError() {
        return error;
    }

    public SearchByNameState getState() {return this.searchByNameState;}

    public void setState(SearchByNameState searchByNameState) {
        this.searchByNameState = searchByNameState;
    }

    /** The property change support for handling property change events. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event for the keyword input.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("keywordInput", null, this.keywordInput);
    }

    /**
     * Fires a property change event for recommended movies.
     */
    public void fireRecommendedMoviesChanged() {
        support.firePropertyChange("recommendedMovies", null, keywordInput);
    }

    /**
     * Adds a property change listener.
     *
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
