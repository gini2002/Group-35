package usecase_adaptor.GetDetailOfMovie;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GetDetailMovieViewModel extends ViewModel {
    /** title of the movie. */
    private String title;
    /** overview of the movie. */
    private String overview;

    /** genre of the movie. */
    private List<String> genre;
    /** poster url of the movie. */
    private String poster_path;
    /** The user that is logged in currently. */
    private String loggedinusername;
    /** id of the movie. */
    private int id;
    /** The label for removing the movie from the watchlist. */
    public static final String DELETE_WATCHLIST_MOVIE_LABEL = "Remove From Watchlist";
    /** The label for adding the movie to the watchlist. */
    public static final String ADD_WATCH_LIST_BUTTON_LABEL = "Add to watchlist";
    /** The state of get movie detail. */
    private GetDetailMovieState getDetailMovieState = new GetDetailMovieState();
    /** The property change support for handling property change events. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * Constructs a GetDetailMovieViewModel with the specified view name.
     */
    public GetDetailMovieViewModel(){
        super("Details");
    }

    /**
     * Sets the title of the movie.
     * @param title The title of the movie.
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * Sets the overview of the movie.
     * @param overview The overview of the movie.
     */
    public void setOverview(String overview){
        this.overview = overview;
    }

    /**
     * Sets the genre of the movie.
     * @param genre The genre of the movie.
     */
    public void setGenre(List<String> genre){
        this.genre = genre;
    }

    /**
     * Sets the name of the user that is currently logged in.
     * @param loggedinusername The name of the user that is currently logged in.
     */
    public void setLoggedinusername(String loggedinusername) {
        this.loggedinusername = loggedinusername;
    }

    /**
     * Sets the poster url of the movie.
     * @param poster_path The poster url of the movie.
     */
    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}

    /**
     * Sets the id of the movie.
     * @param id The id of the movie.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Fires a property change event.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.getDetailMovieState);
    }

    /**
     * Adds a property change listener.
     * @param listener The property change listener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Sets the getDetailmoviestate.
     * @param getDetailMovieState The state of the user case.
     */
    public void setGetDetailMovieState(GetDetailMovieState getDetailMovieState) {
        this.getDetailMovieState = getDetailMovieState;
    }

    /**
     * Retrieves the current getDetailMovieState.
     * @return The current getDetailMovieState.
     */
    public GetDetailMovieState getGetDetailMovieState() {
        return getDetailMovieState;
    }
    /**
     * Retrieves the genre of the movie.
     * @return The genre of the movie.
     */
    public List<String> getGenre() {
        return genre;
    }
    /**
     * Retrieves the current keyword input value.
     * @return The current keyword input value.
     */
    public String getOverview() {
        return overview;
    }
    /**
     * Retrieves the title of the movie.
     * @return The title of the movie.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Retrieves the poster url of the movie.
     * @return The poster url of the movie.
     */
    public String getPoster_path() {
        return poster_path;
    }
    /**
     * Retrieves the id of the movie.
     * @return The id of the movie.
     */
    public int getId() {return id;}
    /**
     * Retrieves the name of user that is currently logged in.
     * @return the name of user that is currently logged in.
     */
    public String getLoggedinusername() {return loggedinusername;}
}