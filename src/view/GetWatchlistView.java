package view;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.GetWatchlist.GetWatchListState;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.GetWatchlist.GetWatchlistController;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ViewManagerModel;

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

    private final ViewManagerModel viewManagerModel;
    private final MainMenuViewModel mainMenuViewModel;

    final JButton backToMainMenu;


    public GetWatchlistView(GetWatchListViewmodel getwatchListViewmodel,
                            GetWatchlistController getwatchlistController,
                            GetDetailMovieController getdetailMovieController,
                            GetDetailMovieViewModel getdetailMovieViewModel,
                            ViewManagerModel viewManagerModel,
                            MainMenuViewModel mainMenuViewModel) {
        this.getWatchlistController = getwatchlistController;
        this.getDetailMovieController = getdetailMovieController;
        this.getDetailMovieViewModel = getdetailMovieViewModel;
        this.getWatchListViewModel = getwatchListViewmodel;
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.getWatchListViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel("Watchlist");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        List<String> names = getWatchListViewModel.getNames();
        backToMainMenu = new JButton(GetWatchListViewmodel.MAIN_MENU_BUTTON_LABEL);
        buttons.add(backToMainMenu);
        backToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backToMainMenu)) {
                    viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        for (int i = 0; i < names.size(); i++) {
            JButton button = new JButton(GetWatchListViewmodel.DETAIL_MOVIE_LABEL + names.get(i));
            buttons.add(button);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(button)) {
                        String name = button.getName();
                        int id = getWatchListViewModel.getIds().get(finalI);
                        String loggedinusername = getWatchListViewModel.getLogged_in_username();
                        getDetailMovieController.execute(id, loggedinusername);
                        // JOptionPane.showMessageDialog(new JFrame(), getDetailMovieViewModel.getOverview());
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
        if (evt.getPropertyName().equals("GetWatchlistState")){
            GetWatchListState getWatchListState = (GetWatchListState) evt.getNewValue();
            if (getWatchListState.getGetWatchListError() != null){
                JOptionPane.showMessageDialog(this, getWatchListState.getGetWatchListError());
            } else {
                JOptionPane.showMessageDialog(this, "get watchlist for" + getWatchListState.getLoggedinusername());
            }
        }
    }
}
