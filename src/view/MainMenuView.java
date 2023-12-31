package view;

import usecase_adaptor.GetWatchlist.GetWatchListState;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.GetWatchlist.GetWatchlistController;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameState;
import usecase_adaptor.RecommendMovieWithoutFilter.WithoutFilterViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistState;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel{

    public final String viewName = "Main_menu";

    final JButton shareWatchlistButton;
    final JButton getWatchlistButton;
    final JButton searchMovieButton;
    final JButton recommendMovieButton;

    private final ViewManagerModel viewManagerModel;

    private final MainMenuViewModel mainMenuViewModel;

    private final ShareWatchlistViewModel shareWatchlistViewModel;


    public MainMenuView(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel,
                        ShareWatchlistViewModel shareWatchlistViewModel, SearchByNameViewModel searchByNameViewModel,
                        WithoutFilterViewModel withoutFilterViewModel,
                        GetWatchListViewmodel getWatchListViewmodel,
                        GetWatchlistController getWatchlistController
    ) {
        // ShareWatchlistViewModel shareWatchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.shareWatchlistViewModel = shareWatchlistViewModel;

        JLabel title = new JLabel("main menu screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // add buttons that links to use case's view.
        JPanel buttons = new JPanel();
        shareWatchlistButton = new JButton(mainMenuViewModel.SHARE_WATCHLIST_BUTTON_LABEL);
        buttons.add(shareWatchlistButton);

        shareWatchlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // give username to ShareWatchlist view model state
                ShareWatchlistState shareWatchlistState = shareWatchlistViewModel.getState();
                String loggedUserName = mainMenuViewModel.getState().getUserName();
                System.out.println("main menu: current user " + loggedUserName);
                shareWatchlistState.setLoggedUserName(loggedUserName);

                // change to ShareWatchlist View
                viewManagerModel.setActiveView(shareWatchlistViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        // add a new button for get watchlist
        getWatchlistButton = new JButton(mainMenuViewModel.Get_WATCHLIST_BUTTON_LABEL);
        buttons.add(shareWatchlistButton);
        getWatchlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetWatchListState getWatchListState = getWatchListViewmodel.getState();
                String loggedinuesername = mainMenuViewModel.getState().getUserName();
                getWatchListState.setLoggedinusername(loggedinuesername);
                getWatchlistController.execute(loggedinuesername);
                getWatchListViewmodel.setState(getWatchListState);
                viewManagerModel.setActiveView(getWatchListViewmodel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });


        buttons.add(getWatchlistButton);

        searchMovieButton = new JButton(mainMenuViewModel.SEARCH_MOVIE_BUTTON_LABEL);
        buttons.add(searchMovieButton);

        searchMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchByNameState searchByNameState = searchByNameViewModel.getState();
                String loggedinusername = mainMenuViewModel.getState().getUserName();
                searchByNameState.setUsername(loggedinusername);
                searchByNameViewModel.setState(searchByNameState);
                viewManagerModel.setActiveView(searchByNameViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        recommendMovieButton = new JButton(mainMenuViewModel.RECOMMEND_MOVIE_BUTTON_LABEL);
        buttons.add(recommendMovieButton);
        recommendMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView(withoutFilterViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        this.add(title);
        this.add(buttons);
    }


}

