package usecase_adaptor.ShareWatchlist;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShareWatchlistViewModel extends ViewModel {

    private ShareWatchlistState state = new ShareWatchlistState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ShareWatchlistViewModel() {super("ShareWatchlist");}

    public ShareWatchlistState getState() {return state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
