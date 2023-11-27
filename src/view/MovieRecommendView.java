package view;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameController;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.SearchList.SearchListViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MovieRecommendView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "movie_recommendation";
    private final SearchByNameViewModel viewModel;

    final JTextField keywordInputField = new JTextField(15);
    private final JLabel errorLabel = new JLabel();

    private final ViewManagerModel viewManagerModel;


    final JButton searchButton;
    final JButton searchListButton;

    private final SearchByNameController controller;

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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchButton)) {
                    String keyword = keywordInputField.getText();
                    controller.execute(keyword);
                    System.out.println(keyword);
//                    MovieResultViewModel movieResultViewModel = new MovieResultViewModel();
//                    MovieResultView resultView = new MovieResultView(movieResultViewModel, viewModel, viewManagerModel);
//                    showMovieResultView(movieResultViewModel, viewModel, viewManagerModel);
                    viewManagerModel.setActiveView("movie_result");
                    viewManagerModel.firePropertyChanged();
//                    revalidate();
//                    repaint();
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(keywordPanel);
        this.add(errorLabel);
        this.add(buttons);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("error".equals(evt.getPropertyName())) {
            System.out.println(evt.getPropertyName());
            String error = (String) evt.getNewValue();
            errorLabel.setText(error);
        } else if ("recommendedMovies".equals(evt.getPropertyName())) {
            // Update the recommended movies area when the property changes
//            List<Movie> recommendedMovies = (List<Movie>) evt.getNewValue();
//            updateRecommendedMoviesArea(recommendedMovies);
//            MovieResultViewModel movieResultViewModel = new MovieResultViewModel();
//            showMovieResultView(movieResultViewModel);

//            System.out.println(evt.getPropertyName());
//            MovieResultViewModel movieResultViewModel = (MovieResultViewModel) evt.getNewValue();
//            showMovieResultView(movieResultViewModel);
        }
    }

//    private void showMovieResultView(MovieResultViewModel movieResultViewModel, SearchByNameViewModel viewModel, ViewManagerModel viewManagerModel) {
//        SwingUtilities.invokeLater(() -> {
//            MovieResultView resultView = new MovieResultView(movieResultViewModel, viewModel, viewManagerModel);
//            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
//            frame.getContentPane().removeAll();
//            frame.getContentPane().add(resultView);
//            frame.revalidate();
//            frame.repaint();
////            resultView.updateView();
////            frame.setVisible(true);
//        });
//    }
//
//    private void showSearchListView(SearchListViewModel searchListViewModel, SearchByNameViewModel viewModel) {
//        SwingUtilities.invokeLater(() -> {
//            SearchListView searchListView = new SearchListView(searchListViewModel);
//            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
//            frame.getContentPane().removeAll();
//            frame.getContentPane().add(searchListView);
//            frame.revalidate();
//            frame.repaint();
////            resultView.updateView();
////            frame.setVisible(true);
//        });
//    }

}
