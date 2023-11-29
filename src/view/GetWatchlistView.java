package view;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.GetWatchlist.GetWatchListState;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.GetWatchlist.GetWatchlistController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class GetWatchlistView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "Watchlist";
    private final GetWatchListViewmodel getWatchListViewModel;

    private final GetDetailMovieViewModel getDetailMovieViewModel;

    private final GetWatchlistController getWatchlistController;

    private final GetDetailMovieController getDetailMovieController;

    final JButton backToMainMenu;


    public GetWatchlistView(GetWatchListViewmodel viewModel,
                            GetWatchlistController getwatchlistController,
                            GetDetailMovieController getdetailMovieController,
                            GetDetailMovieViewModel getDetailMovieViewModel) {
        this.getWatchlistController = getwatchlistController;
        this.getDetailMovieController = getdetailMovieController;
        this.getDetailMovieViewModel = getDetailMovieViewModel;
        this.getWatchListViewModel = viewModel;
        this.getWatchListViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel("Watchlist");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        List<String> names = getWatchListViewModel.getNames();
        backToMainMenu = new JButton(GetWatchListViewmodel.MAIN_MENU_BUTTON_LABEL);
        buttons.add(backToMainMenu);
        for (String name: names) {
            JButton button = new JButton(GetWatchListViewmodel.DETAIL_MOVIE_LABEL + name);
            buttons.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(button)) {
                        String name = button.getName();
                        int id = getWatchListViewModel.getIds().get();
                        String loggedinusername = getWatchListViewModel.getLogged_in_username();
                        // TODO: movie ID needed
                        getDetailMovieController.execute(name, id, loggedinusername);
                        // JOptionPane.showMessageDialog(new JFrame(), getDetailMovieViewModel.getOverview());
                        // TODO:add other infos (maybe needed
                    }
                }
            });
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click " + e.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetWatchListState getWatchListState = (GetWatchListState) evt.getNewValue();
        if (getWatchListState.getGetWatchListError() != null){
            JOptionPane.showMessageDialog(this, getWatchListState.getGetWatchListError());
        }
    }
}
