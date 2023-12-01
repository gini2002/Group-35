package view;

import usecase_adaptor.MovieSearchByKeyword.SearchByNameController;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The MovieRecommendView class represents the graphical user interface for the movie recommendation screen.
 * It provides a form for entering a keyword, buttons for initiating a search and viewing the search list, and displays error messages.
 */
public class MovieRecommendView extends JPanel implements ActionListener, PropertyChangeListener {
    /** The name of the view. */
    public final String viewName = "movie_recommendation";

    /** The view model associated with the movie recommendation screen. */
    private final SearchByNameViewModel viewModel;

    /** The input field for entering the search keyword. */
    final JTextField keywordInputField = new JTextField(15);

    /** The label for displaying error messages. */
    final JLabel errorLabel = new JLabel();

    /** The model for managing the active view in the application. */
    private final ViewManagerModel viewManagerModel;

    /** The button for initiating a search. */
    final JButton searchButton;

    /** The button for viewing the search list. */
    final JButton searchListButton;
    final JButton mainMenuBtn;

    /** The controller for handling user interactions on the movie recommendation screen. */
    private final SearchByNameController controller;

    /**
     * Constructs a MovieRecommendView with the specified view model, controller, and view manager model.
     *
     * @param viewModel The view model associated with the movie recommendation screen.
     * @param controller The controller for handling user interactions on the movie recommendation screen.
     * @param viewManagerModel The model for managing the active view in the application.
     */
    public MovieRecommendView(SearchByNameViewModel viewModel, SearchByNameController controller, ViewManagerModel viewManagerModel) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel("Movie Recommendation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel keywordPanel = new JPanel();
        keywordPanel.add(new JLabel("Enter your keyword here:"));
        keywordPanel.add(keywordInputField);

        JPanel buttons = new JPanel();
        searchButton = new JButton(viewModel.SEARCH_BUTTON_LABEL);
        buttons.add(searchButton);
        searchListButton = new JButton(viewModel.SEARCH_LIST_BUTTON_LABEL);
        buttons.add(searchListButton);
        mainMenuBtn = new JButton(viewModel.MAIN_MENU_BUTTON_LABEL);
        buttons.add(mainMenuBtn);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchButton)) {
                    String keyword = keywordInputField.getText();
                    controller.execute(keyword);
                    System.out.println(keyword);
                    viewManagerModel.setActiveView("movie_result");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        searchListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchListButton)) {
                    viewManagerModel.setActiveView("search_list");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        mainMenuBtn.addActionListener(new ActionListener() {
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
     * Handles the actionPerformed event.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    /**
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent that occurred.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("error".equals(evt.getPropertyName())) {
            System.out.println(evt.getPropertyName());
            String error = (String) evt.getNewValue();
            errorLabel.setText(error);
        } else if ("recommendedMovies".equals(evt.getPropertyName())) {
            // Update the recommended movies area when the property changes
        }
    }
}
