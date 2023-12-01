package usecase_adaptor.AddToWatchlist;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddToWatchlistViewModel extends ViewModel {

    private AddToWatchlistState state = new AddToWatchlistState();

    public static String ADD_WATCH_LIST_BUTTON_LABEL = "add to watchlist";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * initiate view model.
     */
    public AddToWatchlistViewModel() {super("AddToWatchlist");}

    /**
     *
     * @return the state stored in view model.
     */
    public AddToWatchlistState getState() {
        return state;
    }

    /**
     * announce change in state to listeners.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
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
