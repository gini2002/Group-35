package usecase_adaptor.AddToWatchlist;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddToWatchlistViewModel extends ViewModel {

    private AddToWatchlistState state = new AddToWatchlistState();

    public static String ADD_WATCH_LIST_BUTTON_LABEL = "add to watchlist";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddToWatchlistViewModel() {super("AddToWatchlist");}

    public AddToWatchlistState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
