package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;

public class MovieResultView extends JPanel implements PropertyChangeListener{
    public final String viewName = "movie_result";
    private final MovieResultViewModel viewModel;
    private final SearchByNameViewModel searchByNameViewModel;


    public DefaultListModel<String> listModel;
    public JList<String> movieList;

    //    private JTextArea movieTextArea = new JTextArea();
    private JLabel errorLabel;

    final JButton mainMenuBtn;
    private final ViewManagerModel viewManagerModel;

    public MovieResultView(MovieResultViewModel viewModel, SearchByNameViewModel searchByNameViewModel, ViewManagerModel viewManagerModel) {
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
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JPanel moviesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel movieListPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        movieList = new JList<>(listModel);
        listModel.clear();
        String [] recommendedMovies = searchByNameViewModel.getRecommendedMovies();


        // Update movie list
        if (recommendedMovies != null) {
            listModel.addAll(Arrays.asList(recommendedMovies));
        }

        // Update error label
        movieList.setModel(listModel);

        // Add ActionListener to each button
//        movieList.addListSelectionListener(e -> {
//            int selectedIndex = movieList.getSelectedIndex();
//            if (selectedIndex != -1) {
//                String selectedMovie = listModel.getElementAt(selectedIndex);
//                showMovieDialog(selectedMovie);
//            }
//        });


        // Create a custom cell renderer with a button
//        movieList.setCellRenderer(new ButtonRenderer());


        JScrollPane scrollPane = new JScrollPane(movieList);
        movieListPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(movieListPanel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        String error = viewModel.getError();


        // Error label
        errorLabel = new JLabel(error);
//        this.add(errorLabel, BorderLayout.SOUTH);

        setVisible(true);


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        List<String> recommendedMovies = viewModel.getRecommendedMovies();
//        String error = viewModel.getError();
//        StringBuilder movieText = new StringBuilder();
//
//        // Update movie text area
//        if (recommendedMovies != null) {
//            for (String name : recommendedMovies) {
//                movieText.append(name).append("\n");
//                System.out.println(name);
//            }
//            movieTextArea.setText(movieText.toString());
//        }
//
//        // Update error label
//        errorLabel.setText(error);
//
//        // Repaint the UI
//        revalidate();
//        repaint();
//        setVisible(true);
//
//        updateView();
//        if ("recommendedMovies".equals(evt.getPropertyName())) {
//            updateView();
//        }


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

//    private void showMovieDialog(String movieName) {
//        JFrame parent = new JFrame();
//        JButton button = new JButton();
//
//        button.setText("Click me to show movie info!");
//        parent.add(button);
//        parent.pack();
//        parent.setVisible(true);
//
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                JOptionPane.showMessageDialog(parent, "Movie Info");
//            }
//        });
//    }

//    private class ButtonRenderer extends DefaultListCellRenderer {
//        @Override
//        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//            JButton button = new JButton(value.toString());
//            button.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
//            button.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
//            return button;
//        }
//    }

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


}






