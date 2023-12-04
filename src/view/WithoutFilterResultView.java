package view;

import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterResultState;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterResultViewModel;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

/**
 * Represents the view for displaying the results of the "Recommend Movie Without Filter" feature.
 * This class provides a user interface for presenting recommended movies based on user input,
 * navigating back to the main menu, and handling interactions with individual movie items.
 */
public class WithoutFilterResultView extends JPanel implements PropertyChangeListener {
    /** The identifier for this view, used in the view management system. */
    public final String viewName = "without_result";

    /** The view model providing data and state for this view. */
    private final WithoutFilterResultViewModel viewModel;

    /** The view model for the "Recommend Movie Without Filter" feature. */
    private final WithoutFilterViewModel withoutFilterViewModel;

    /** The view model for the "Recommend Movie Without Filter" feature. */

    private final ViewManagerModel viewManagerModel;

    /** The button for returning to the main menu. */
    final JButton mainMenuBtn;

    /** The controller for detailed movie information retrieval. */
    private final GetDetailMovieController controller;

    /** The model for the list of recommended movies. */
    public DefaultListModel<String> listModel;

    /** The list component displaying the recommended movies. */
    public JList<String> movieList;

    //    private JTextArea movieTextArea = new JTextArea();

    /** The label for displaying error messages. */
    JLabel errorLabel;

    /**
     * Constructs a new WithoutFilterResultView with the specified view models, view manager model, and controller.
     * Initializes the UI components and sets up event listeners and data bindings.
     *
     * @param viewModel The view model for this view, managing the result state.
     * @param withoutFilterViewModel The view model for handling movie recommendations without filter.
     * @param viewManagerModel The model for managing navigation and active views in the application.
     * @param getDetailMovieController The controller for handling detailed movie information retrieval.
     */
    public WithoutFilterResultView(WithoutFilterResultViewModel viewModel, WithoutFilterViewModel withoutFilterViewModel, ViewManagerModel viewManagerModel, GetDetailMovieController getDetailMovieController) {
        this.controller = getDetailMovieController;
        this.withoutFilterViewModel = withoutFilterViewModel;
        setVisible(false);
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        JPanel buttons = new JPanel(new FlowLayout());
        mainMenuBtn = new JButton(MovieResultViewModel.MAIN_MENU_LABEL);
        buttons.add(mainMenuBtn);

        mainMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(mainMenuBtn)) {
                    viewManagerModel.setActiveView("movie_recommendation_filter");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

//        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Your Movie Recommendations");
        titlePanel.add(title);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel movieListPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        movieList = new JList<>(listModel);
        listModel.clear();
        String[] withoutFilterMovies = withoutFilterViewModel.getRecommendedMovies();


        // Update movie list
        if (withoutFilterMovies != null) {
            listModel.addAll(Arrays.asList(withoutFilterMovies));
        }

        // Update error label
        movieList.setModel(listModel);

        movieList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = movieList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        // Get the selected movie name
                        String selectedMovie = listModel.getElementAt(selectedIndex);

                        // Handle the click event, e.g., switch to another view
                        handleMovieClick(selectedMovie);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(movieList);
        movieListPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(movieListPanel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        String error = viewModel.getError();

        // Error label
        errorLabel = new JLabel(error);
        // this.add(errorLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Responds to property changes in the bound properties, updating the view accordingly.
     *
     * @param evt The property change event containing information about the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        switch (evt.getPropertyName()) {
            case "withoutFilterMovies":
                WithoutFilterResultState state = viewModel.getState();
                if (state.getError() != null) {
                    JOptionPane.showMessageDialog(this, state.getError());
                } else {
                    updateView();
                }
            case "error":
                // Handle error property change if needed
                break;
            // Add more cases if there are other properties to handle
        }
    }

    /**
     * Updates the view components with the latest data from the view models.
     * This method is called to refresh the UI elements such as the movie list and error messages.
     */
    public void updateView() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Updating view...");

            // Print some information for debugging
            System.out.println("WithoutFilterViewModel: " + withoutFilterViewModel);
            System.out.println("viewModel: " + viewModel);

            String[] withoutFilterMovies = withoutFilterViewModel.getRecommendedMovies();
            System.out.println("Recommended movies: " + Arrays.toString(withoutFilterMovies));

            if (withoutFilterMovies != null) {
                listModel.clear();
                listModel.addAll(Arrays.asList(withoutFilterMovies));
                movieList.setModel(listModel);

                // Print the contents of listModel for debugging
                System.out.println("Contents of listModel:");
                for (int i = 0; i < listModel.size(); i++) {
                    System.out.println(listModel.getElementAt(i));
                }

                // Update error label
                errorLabel.setText(viewModel.getError());

                // Repaint the UI
                revalidate();
                repaint();
            } else {
                System.out.println("Recommended movies is null.");
            }

            System.out.println("Update complete.");
        });
    }

    /**
     * Handles the selection of a movie from the list.
     * This method is invoked when a user clicks on a movie in the list, triggering further actions like displaying detailed information.
     *
     * @param selectedMovie The title of the selected movie.
     */
    private void handleMovieClick(String selectedMovie) {
        String username = withoutFilterViewModel.getState().getUsername();
        int movie_id = withoutFilterViewModel.getID(selectedMovie);
        controller.execute(movie_id, username);
        viewManagerModel.setActiveView("detail_view");
        viewManagerModel.firePropertyChanged();
        System.out.println("Movie clicked");
    }
}












