package view;

import entity.Movie;
import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.AddToWatchlist.AddToWatchlistState;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;
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

    private final AddToWatchlistViewModel addToWatchlistViewModel;


    public GetDetailMovieView(GetDetailMovieViewModel getDetailMovieViewModel,
                              AddToWatchlistController addToWatchlistController,
                              AddToWatchlistViewModel addToWatchlistViewModel){
        this.getDetailMovieViewModel = getDetailMovieViewModel;
        this.addToWatchlistController = addToWatchlistController;
        this.addToWatchlistViewModel = addToWatchlistViewModel;
        this.addToWatchlistViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel("details");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        JButton addToWatchlist = new JButton(AddToWatchlistViewModel.ADD_WATCH_LIST_BUTTON_LABEL);
        JButton removeFromWatchlist = new JButton(GetDetailMovieViewModel.DELETE_WATCHLIST_MOVIE_LABEL);
        buttons.add(addToWatchlist);
        buttons.add(removeFromWatchlist);

        addToWatchlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(addToWatchlist)) {
                    int id = getDetailMovieViewModel.getId();
                    String name = getDetailMovieViewModel.getTitle();
                    addToWatchlistController.execute(new Movie(name, id), "");
                    //TODO username
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
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof AddToWatchlistState) {
            AddToWatchlistState state = (AddToWatchlistState) evt.getNewValue();
            if (state.getMovieExistError() != null) {
                JOptionPane.showMessageDialog(this, state.getMovieExistError());
            } else {
                JOptionPane.showMessageDialog(this, state.getMessage());
            }
        }
    }
}
