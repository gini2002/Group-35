package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import entity.Movie;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;

/**
 * The MovieResultView class represents the graphical user interface for displaying movie recommendations.
 * It includes a list of recommended movies, a main menu button, and an error label.
 */
public class MovieResultView extends JPanel implements PropertyChangeListener {
    /** The name of the view. */
    public final String viewName = "movie_result";

    /** The view model associated with the movie result screen. */
    private final MovieResultViewModel viewModel;

    /** The view model associated with the movie recommendation screen. */
    private final SearchByNameViewModel searchByNameViewModel;

    /** The model for managing the active view in the application. */
    private final ViewManagerModel viewManagerModel;

    /** The button for navigating back to the main menu. */
    final JButton mainMenuBtn;
    private final GetDetailMovieController controller;

    /** The list model for displaying recommended movies. */
    public DefaultListModel<String> listModel;


    /** The JList component for displaying recommended movies. */
    public JList<String> movieList;

    /** The label for displaying error messages. */
    JLabel errorLabel;

    /**
     * Constructs a MovieResultView with the specified view models and view manager model.
     *
     * @param viewModel The view model associated with the movie result screen.
     * @param searchByNameViewModel The view model associated with the movie recommendation screen.
     * @param viewManagerModel The model for managing the active view in the application.
     */
    public MovieResultView(MovieResultViewModel viewModel, SearchByNameViewModel searchByNameViewModel, ViewManagerModel viewManagerModel, GetDetailMovieController getDetailMovieController) {
        this.controller = getDetailMovieController;
        this.searchByNameViewModel = searchByNameViewModel;
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
                    viewManagerModel.setActiveView("movie_recommendation");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Your Movie Recommendations");
        titlePanel.add(title);

        JPanel movieListPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        movieList = new JList<>(listModel);
        listModel.clear();

        String[] recommendedMovies = searchByNameViewModel.getRecommendedMovies();

        // Update movie list
        if (recommendedMovies != null) {
            listModel.addAll(Arrays.asList(recommendedMovies));
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
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent that occurred.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "recommendedMovies":
                updateView();
                break;
            case "error":
                // Handle error property change if needed
                break;
            // Add more cases if there are other properties to handle
        }
    }

    /**
     * Updates the view with new recommended movies and error messages.
     */
    public void updateView() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Updating view...");

            // Print some information for debugging
            System.out.println("searchByNameViewModel: " + searchByNameViewModel);
            System.out.println("viewModel: " + viewModel);

            String[] recommendedMovies = searchByNameViewModel.getRecommendedMovies();
            System.out.println("Recommended movies: " + Arrays.toString(recommendedMovies));

            if (recommendedMovies != null) {
                listModel.clear();
                listModel.addAll(Arrays.asList(recommendedMovies));
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

    private void handleMovieClick(String selectedMovie) {
        String username = searchByNameViewModel.getState().getUsername();
        int movie_id = searchByNameViewModel.getID(selectedMovie);
        controller.execute(movie_id, username);
        viewManagerModel.setActiveView("detail_view");
        viewManagerModel.firePropertyChanged();
        System.out.println("Movie clicked");
    }
}
