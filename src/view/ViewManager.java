package view;

import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The ViewManager class manages the switching of views in a CardLayout based on changes in the active view model.
 * It listens for property change events from the ViewManagerModel and updates the displayed view accordingly.
 */
public class ViewManager implements PropertyChangeListener {

    /** The CardLayout for managing multiple views. */
    private final CardLayout cardLayout;

    /** The container JPanel that holds multiple views managed by the CardLayout. */
    private final JPanel views;

    /** The model for managing the active view in the application. */
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a ViewManager with the specified views, CardLayout, and ViewManagerModel.
     *
     * @param views The container JPanel holding multiple views managed by the CardLayout.
     * @param cardLayout The CardLayout for managing multiple views.
     * @param viewManagerModel The model for managing the active view in the application.
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    /**
     * Handles property change events, updating the displayed view based on changes in the active view model.
     *
     * @param evt The PropertyChangeEvent that occurred.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }
    }
}
