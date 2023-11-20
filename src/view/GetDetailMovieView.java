package view;

import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GetDetailMovieView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewname = "";
    private final GetDetailMovieViewModel getDetailMovieViewModel;
    private final AddToWatchlistController addToWatchlistController;


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
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
