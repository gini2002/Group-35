package usecase_adaptor.ShareWatchlist;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShareWatchlistViewModel extends ViewModel {

    private ShareWatchlistState state = new ShareWatchlistState();

    public static final String USER_NAME_LABEL = "enter receiver's username";

    public static final String BACK_LABEL = "back to main menu";

    public static final String SEARCH_USERNAME_LABEL = "search";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * initiate the view model.
     */
    public ShareWatchlistViewModel() {super("ShareWatchlist");}

    /**
     *
     * @return the state stored in view model.
     */
    public ShareWatchlistState getState() {return state;}

    /**
     * save state.
     * @param state the state saved user information.
     */
    public void setState(ShareWatchlistState state) {this.state = state;}


    /**
     * announce changes to listeners.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("ShareWatchlistState", null, this.state);
    }

    /**
     * add listener.
     * @param listener who listens to this view model.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
