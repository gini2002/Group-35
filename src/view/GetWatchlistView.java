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
import java.util.Arrays;
import java.util.List;

public class GetWatchlistView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "Watchlist";
    private final GetWatchListViewmodel getWatchListViewModel;

    private final GetDetailMovieViewModel getDetailMovieViewModel;

    private final GetWatchlistController getWatchlistController;

    private final GetDetailMovieController getDetailMovieController;

    private final ViewManagerModel viewManagerModel;
    private final MainMenuViewModel mainMenuViewModel;

    JButton backToMainMenu;


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
        List<String> names = getWatchListViewModel.getNames();
        getWatchListViewModel.firePropertyChanged();
        for (int i = 0; i < names.size(); i++) {
            JButton button = new JButton(GetWatchListViewmodel.DETAIL_MOVIE_LABEL + names.get(i));
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
            buttons.add(button);
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    public void updateView() {
        SwingUtilities.invokeLater(() -> {
            JPanel buttons = new JPanel();
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
            List<String> names = getWatchListViewModel.getNames();
            if (names != null) {this.removeAll();}
            JLabel title = new JLabel("Watchlist of " + getWatchListViewModel.getLogged_in_username());
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
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
                int finalI = i;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(button)) {
                            String name = button.getName();
                            int id = getWatchListViewModel.getIds().get(finalI);
                            String loggedinusername = getWatchListViewModel.getLogged_in_username();
                            getDetailMovieController.execute(id, loggedinusername);
                            viewManagerModel.setActiveView(getDetailMovieViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                            // JOptionPane.showMessageDialog(new JFrame(), getDetailMovieViewModel.getOverview());
                        }
                    }
                });
                buttons.add(button);
            }
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(title);
            this.add(buttons);
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click " + e.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "state":
                updateView();
                break;
            case "error":
                break;
        }
        //if (evt.getNewValue() instanceof GetWatchListState){
        //            GetWatchListState getWatchListState = (GetWatchListState) evt.getNewValue();
        //            System.out.println("22222");
        //            if (getWatchListState.getGetWatchListError() != null){
        //                JOptionPane.showMessageDialog(this, getWatchListState.getGetWatchListError());
        //            } else {
        //                viewManagerModel.setActiveView(getWatchListViewModel.getViewName());
        //            }
        //        }
    }
}
