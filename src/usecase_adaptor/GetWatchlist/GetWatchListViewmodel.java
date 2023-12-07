package usecase_adaptor.GetWatchlist;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GetWatchListViewmodel extends ViewModel {
    /** The titles of the movies in the watchlist of certain user. */
    private List<String> names = new ArrayList<>();
    /** The poster paths of the movies in the watchlist of certain user. */
    private List<String> posters;
    /** The ids of the movies in the watchlist of certain user. */
    private List<Integer> ids;
    /** The username of user that is logged in currently */
    private String logged_in_username;
    /** The error message. */
    private String error;
    /** The label for buttons for get detail of movie. */
    public static final String DETAIL_MOVIE_LABEL = "";
    /** The label for buttons for go bak to the main menu. */
    public static final String MAIN_MENU_BUTTON_LABEL = "BACK TO MAIN MENU";
    /** The property change support for handling property change events. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /** The state of get watchlist. */
    private GetWatchListState state = new GetWatchListState();
    /**
     * Constructs a GetWatchListViewmodel with the specified view name.
     */
    public GetWatchListViewmodel() {
        super("Watchlist");
    }
    /**
     * Fires a property change event.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
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
     * Retrieves the current getWatchListState
     * @return The current getWatchListState
     */
    public GetWatchListState getState(){
        return state;
    }
    /**
     * Sets the getWatchListState
     * @param state The state of the use case.
     */
    public void setState(GetWatchListState state) {
        this.state = state;
    }

    /**
     * Sets the watchlist information including titles, ids and poster paths of movies in the watchlist of the user.
     * @param names The titles of the movies in the watchlist of certain user.
     * @param ids The ids of the movies in the watchlist of certain user.
     * @param posters The poster paths of the movies in the watchlist of certain user.
     */
    public void setWatchlist(List<String> names, List<String> posters, List<Integer> ids){
        this.names = names;
        this.posters = posters;
        this.ids = ids;
        firePropertyChanged();
    }
    /**
     * Retrieves the titles of the movies in the watchlist of certain user.
     * @return The titles of the movies in the watchlist of certain user.
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * Retrieves the poster paths of the movies in the watchlist of certain user.
     * @return The poster paths of the movies in the watchlist of certain user.
     */
    public List<String> getPosters() {
        return posters;
    }

    /**
     * Sets the name of the user that is currently logged in.
     * @param logged_in_username The name of the user that is currently logged in.
     */
    public void setLogged_in_username(String logged_in_username) {
        this.logged_in_username = logged_in_username;
    }
    /**
     * Retrieves the name of user that is currently logged in.
     * @return the name of user that is currently logged in.
     */
    public String getLogged_in_username() {
        return logged_in_username;
    }

    /**
     * Retrieves the ids of the movies in the watchlist of certain user.
     * @return The ids of the movies in the watchlist of certain user.
     */
    public List<Integer> getIds() {
        return ids;
    }
}
