package view;

import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterResultViewModel;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class WithoutFilterResultView extends JPanel implements PropertyChangeListener{
    public final String viewName = "movie_result";
    private final WithoutFilterResultViewModel viewModel;
    private final WithoutFilterViewModel withoutFilterViewModel;


    public DefaultListModel<String> listModel;
    public JList<String> movieList;

    //    private JTextArea movieTextArea = new JTextArea();
    private JLabel errorLabel;

    public WithoutFilterResultView(WithoutFilterResultViewModel viewModel, WithoutFilterViewModel withoutFilterViewModel) {
        this.withoutFilterViewModel = withoutFilterViewModel;
        setVisible(false);
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Your Movie Recommendations");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // Add ActionListener to each button
        movieList.addListSelectionListener(e -> {
            int selectedIndex = movieList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedMovie = listModel.getElementAt(selectedIndex);
                showMovieDialog(selectedMovie);
            }
        });


        // Create a custom cell renderer with a button
        movieList.setCellRenderer(new ButtonRenderer());


        JScrollPane scrollPane = new JScrollPane(movieList);
        this.add(title, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        String error = viewModel.getError();


        // Error label
        errorLabel = new JLabel(error);
        this.add(errorLabel, BorderLayout.SOUTH);

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
    }

    private void showMovieDialog(String movieName) {
        JFrame parent = new JFrame();
        JButton button = new JButton();

        button.setText("Click me to show movie info!");
        parent.add(button);
        parent.pack();
        parent.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(parent, "Movie Info");
            }
        });
    }

    private class ButtonRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JButton button = new JButton(value.toString());
            button.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            button.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return button;
        }
    }
}



//    public void updateView() {
//        listModel.clear();
//        String[] recommendedMovies = viewModel.getRecommendedMovies();
//        System.out.println("Called in update view: " + Arrays.toString(recommendedMovies));
//        String error = viewModel.getError();
//
//        // Update movie list
//        if (recommendedMovies != null) {
//            listModel.addAll(Arrays.asList(recommendedMovies));
//        }
//
//        // Update error label
//        errorLabel.setText(error);
//        for (int i = 0; i < listModel.size(); i++) {
//            System.out.println(listModel.getElementAt(i));
//        }
//        movieList.setModel(listModel);
//        System.out.println("Contents of JList:");
//        for (int i = 0; i < listModel.size(); i++) {
//            System.out.println(listModel.getElementAt(i));
//        }
//
//        // Repaint the UI
//        revalidate();
//        repaint();
//        setVisible(true);
//    }







