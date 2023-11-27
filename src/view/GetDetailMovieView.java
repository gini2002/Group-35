package view;

import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieState;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.net.MalformedURLException;

public class GetDetailMovieView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewname = "";
    private final GetDetailMovieViewModel getDetailMovieViewModel;
    private final AddToWatchlistController addToWatchlistController;

    JLabel movie_title;

    JLabel overview;

    JLabel genre;

    public GetDetailMovieView(GetDetailMovieViewModel getDetailMovieViewModel,
                              AddToWatchlistController addToWatchlistController){
        this.getDetailMovieViewModel = getDetailMovieViewModel;
        this.addToWatchlistController = addToWatchlistController;


        JLabel title = new JLabel("details");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        JButton addToWatchlist = new JButton(GetDetailMovieViewModel.ADD_WATCH_LIST_BUTTON_LABEL);
        JButton removeFromWatchlist = new JButton(GetDetailMovieViewModel.DELETE_WATCHLIST_MOVIE_LABEL);
        buttons.add(addToWatchlist);
        buttons.add(removeFromWatchlist);
        addToWatchlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(addToWatchlist)) {
                    // TODO: fulfill the add to watchlist button
                }
            }
        });
        removeFromWatchlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(removeFromWatchlist)) {
                    // TODO: fulfill the remove from watchlist button
                }
            }
        });


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Display Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon imageIcon = new ImageIcon(getDetailMovieViewModel.getPoster_path());
            JLabel label = new JLabel(imageIcon);
            frame.add(label);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        movie_title.setText(getDetailMovieViewModel.getTitle());
        overview.setText(getDetailMovieViewModel.getOverview());
        List<String> genre_list = getDetailMovieViewModel.getGenre();
        String genre_text = "";
        for (int i = 0; i< genre_list.size(); i++){
            genre_text = genre_text + genre_list.get(i);
        }
        genre.setText(genre_text);
        this.add(title);
        this.add(buttons);
        this.add(movie_title);
        this.add(overview);
        this.add(genre);
    }

    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click " + e.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetDetailMovieState state = (GetDetailMovieState) evt.getNewValue();
        movie_title.setText(state.getTitle());
    }
}
