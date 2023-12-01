package error_cases;

import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterController;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterResultViewModel;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WithoutFilterView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "movie_recommendation";
    private final WithoutFilterViewModel viewModel;

    final JTextField idInputField = new JTextField(15);
    private final JLabel errorLabel = new JLabel();


    final JButton searchButton;

    private final WithoutFilterController controller;

    public WithoutFilterView(WithoutFilterViewModel viewModel, WithoutFilterController controller) {
        this.controller = controller;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Movie Recommendation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel keywordPanel = new JPanel();
        keywordPanel.add(new JLabel("Enter your watchlist id here:"));
        keywordPanel.add(idInputField);

        JPanel buttons = new JPanel();
        searchButton = new JButton(viewModel.SEARCH_BUTTON_LABEL);
        buttons.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchButton)) {
                    String watchlistid = idInputField.getText();
                    controller.execute(watchlistid);
                    System.out.println(watchlistid);
                    WithoutFilterResultViewModel withoutFilterResultViewModel = new WithoutFilterResultViewModel();
                    showWithoutFilterResultView(withoutFilterResultViewModel, viewModel);

                }
            }
        });


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(keywordPanel);
        this.add(errorLabel);
        this.add(buttons);


    }

    private void showWithoutFilterResultView(WithoutFilterResultViewModel withoutFilterResultViewModel, WithoutFilterViewModel viewModel) {
        SwingUtilities.invokeLater(() -> {
            WithoutFilterResultView resultView = new WithoutFilterResultView(withoutFilterResultViewModel, viewModel);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(resultView);
            frame.revalidate();
            frame.repaint();
//            resultView.updateView();
//            frame.setVisible(true);
        });
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
        } else if ("withoutFilterMovies".equals(evt.getPropertyName())) {
            // Update the recommended movies area when the property changes
//            List<Movie> recommendedMovies = (List<Movie>) evt.getNewValue();
//            updateRecommendedMoviesArea(recommendedMovies);
//            MovieResultViewModel movieResultViewModel = new MovieResultViewModel();
//            showMovieResultView(movieResultViewModel);

//            System.out.println(evt.getPropertyName());
//            MovieResultViewModel movieResultViewModel = (MovieResultViewModel) evt.getNewValue();
//            showMovieResultView(movieResultViewModel);
//        } else if ("shareWatchlistState".equals((evt.getPropertyName()))) {
//            ShareWatchlistState state = (ShareWatchlistState) evt.getNewValue();
//            if (state.getError() != null) {
//                JOptionPane.showMessageDialog(this, state.getError());
//            } else {
//                JOptionPane.showMessageDialog(this, "shared with " + state.getReseiverName());
//            }
//        }
        }

        }

    }

