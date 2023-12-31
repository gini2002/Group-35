package view;

import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterController;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Represents the graphical user interface for the "Recommend Movie Without Filter" feature.
 * This class provides the UI components for entering a username, initiating the movie recommendation process,
 * and navigating back to the main menu. It also displays error messages and updates based on user interactions
 * and changes in the application state.
 */
public class WithoutFilterView extends JPanel implements ActionListener, PropertyChangeListener {
    /** The name of the view, used for identifying this panel in the view management system. */
    public final String viewName = "movie_recommendation_filter";

    /** The view model associated with this view, providing necessary data and state management. */
    private final WithoutFilterViewModel viewModel;

    /** The text field for user input of the username. */
    final JTextField usernameInputField = new JTextField(15);

    /** The label for displaying error messages. */
    final JLabel errorLabel = new JLabel();

    /** The model for managing the active view in the application. */
    private final ViewManagerModel viewManagerModel;

    /** The button for initiating a search. */
    final JButton searchButton;

    /** The button for returning to the main menu. */
    final JButton mainMenuBtn;

    /** The controller that handles the business logic of the movie recommendation process. */
    private final WithoutFilterController controller;

    /**
     * Constructs a new WithoutFilterView with the given view model, controller, and view manager model.
     * Initializes and arranges UI components and sets up event handlers for user interaction.
     *
     * @param viewModel The view model for managing state and data of the view.
     * @param controller The controller for handling the business logic and user requests.
     * @param viewManagerModel The model for managing navigation and active views.
     */
    public WithoutFilterView(WithoutFilterViewModel viewModel, WithoutFilterController controller, ViewManagerModel viewManagerModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel("Movie Recommendation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel keywordPanel = new JPanel();
        keywordPanel.add(new JLabel("Enter your username here:"));
        keywordPanel.add(usernameInputField);

        JPanel buttons = new JPanel();
        searchButton = new JButton(viewModel.SEARCH_BUTTON_LABEL);
        buttons.add(searchButton);
        mainMenuBtn = new JButton(viewModel.MAIN_MENU_BUTTON_LABEL);
        buttons.add(mainMenuBtn);


        searchButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the search button is clicked.
             * Initiates the process of recommending movies based on the entered username.
             *
             * @param evt The action event generated by the search button click.
             */
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchButton)) {
                    String keyword = usernameInputField.getText();
                    controller.execute(keyword);
                    System.out.println(keyword);
                    viewManagerModel.setActiveView("without_result");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        mainMenuBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when the main menu button is clicked.
             * Navigates the user back to the main menu of the application.
             *
             * @param evt The action event generated by the main menu button click.
             */
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(mainMenuBtn)) {
                    viewManagerModel.setActiveView("Main_menu");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(keywordPanel);
        this.add(errorLabel);
        this.add(buttons);
    }

    /**
     * Handles action events such as button clicks in this view.
     *
     * @param e The action event that triggered this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    /**
     * Responds to property changes in the bound properties, updating the view according to the changes.
     *
     * @param evt The property change event that contains information about the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("error".equals(evt.getPropertyName())) {
            System.out.println(evt.getPropertyName());
            String error = (String) evt.getNewValue();
            errorLabel.setText(error);
        } else if ("withoutFilterMovies".equals(evt.getPropertyName())) {
            // Update the recommended movies area when the property changes
        }
    }
}
