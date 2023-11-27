package usecase_adaptor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewManagerModel class represents the model for managing the active view in the application.
 * It provides methods to access and manipulate the active view and supports property change events.
 */
public class ViewManagerModel {
    /** The name of the active view. */
    private String activeViewName;

    /** The property change support for handling property change events. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Retrieves the name of the active view.
     *
     * @return The name of the active view.
     */
    public String getActiveView() {
        return activeViewName;
    }

    /**
     * Sets the name of the active view.
     *
     * @param activeView The name of the active view to be set.
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    /**
     * Fires a property change event for the active view.
     * Notifies registered listeners about changes in the active view.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
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
