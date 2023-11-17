package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import entity.Movie;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;

public class MovieResultView extends JPanel implements PropertyChangeListener{
    public final String viewName = "movie_result";
    private final MovieResultViewModel viewModel;

//    private final JPanel movieList;

//    private final JList<String> movieList;

//    private DefaultListModel<String> listModel = new DefaultListModel<>();
//    private JList<String> movieList = new JList<>();
    private JTextArea movieTextArea = new JTextArea();
    private JLabel errorLabel;

    public MovieResultView(MovieResultViewModel viewModel) {
        setVisible(false);
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Your Movie Recommendations");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Movie list
//        movieList = new JList<>();
//        DefaultListModel<String> listModel = new DefaultListModel<>();
//        listModel.addElement(viewModel.getRecommendedMovies().get(0));

//        movieList = new JList<>(listModel);

//        movieList.setModel(listModel);
//        movieList.setPreferredSize(new Dimension(300, 200));
//        JScrollPane scrollPane = new JScrollPane(movieList);

        JScrollPane scrollPane = new JScrollPane(movieTextArea);
        movieTextArea.setEditable(false);
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
//        add(scrollPane, BorderLayout.CENTER);


        String error = viewModel.getError();

//        listModel.addElement(recommendedMovies);

        // Error label
        errorLabel = new JLabel();
        add(errorLabel, BorderLayout.SOUTH);

        propertyChange(null);

//        updateView();
//        add(errorLabel, BorderLayout.SOUTH);

//        this.add(title, BorderLayout.NORTH);
//        this.add(scrollPane, BorderLayout.CENTER);

//        updateView();

    }

//    private void updateView() {
//        List<String> recommendedMovies = viewModel.getRecommendedMovies();
//        String error = viewModel.getError();
//        StringBuilder movieText = new StringBuilder();
//
//        // Update movie list
//        if (recommendedMovies != null) {
//            for (String name: recommendedMovies) {
//                System.out.println(name);
//                movieText.append(name).append("\n");
//            }
////        movieList.setPreferredSize(new Dimension(300, 200));
//            movieTextArea.setText(movieText.toString());
//        }
//
//        // Update error label
//        errorLabel.setText(error);
//
//        // Repaint the UI
//        revalidate();
//        repaint();
////        setVisible(true);
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        List<String> recommendedMovies = viewModel.getRecommendedMovies();
        String error = viewModel.getError();
        StringBuilder movieText = new StringBuilder();

        // Update movie text area
        if (recommendedMovies != null) {
            for (String name : recommendedMovies) {
                movieText.append(name).append("\n");
                System.out.println(name);
            }
            movieTextArea.setText(movieText.toString());
        }

        // Update error label
        errorLabel.setText(error);

        // Repaint the UI
        revalidate();
        repaint();
        setVisible(true);
    }
}



