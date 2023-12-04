package usecase_adaptor.DeleteWatchlist;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * The view model for the delete watchlist use case in the application.
 * It encapsulates the state and the property change support mechanism.
 * This class is responsible for maintaining the state of the UI related
 * to the delete watchlist functionality and notifying observers about changes in this state.
 */
public class DeleteWatchlistViewModel extends ViewModel {

    private DeleteWatchlistState state = new DeleteWatchlistState();

    public static String DELETE_WATCHLIST_BUTTON_LABEL = "delete from watchlist";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new DeleteWatchlistViewModel.
     * Initializes the view model with the default state and sets the title for the view.
     */
    public DeleteWatchlistViewModel() {super("DeleteWatchlist");}

    /**
     * Retrieves the current state of the delete watchlist process.
     * This state includes any messages or errors related to the process.
     *
     * @return The current state of the delete watchlist process.
     */
    public DeleteWatchlistState getState() {
        return state;
    }

    /**
     * Notifies all registered listeners about a change in the state.
     * This method is used to update the UI when the state of the delete watchlist process changes.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to this view model.
     * Listeners are notified of changes to the state of the delete watchlist process.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}