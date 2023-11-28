package view;

import usecase_adaptor.MainMenu.MainMenuState;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistState;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel{

    public final String viewName = "Main_menu";

    final JButton shareWatchlistButton;

    private final ViewManagerModel viewManagerModel;

    private final MainMenuViewModel mainMenuViewModel;

    private final ShareWatchlistViewModel shareWatchlistViewModel;

    public MainMenuView(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel,
                        ShareWatchlistViewModel shareWatchlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.shareWatchlistViewModel = shareWatchlistViewModel;

        JLabel title = new JLabel("Main_menu");
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
                String loggedUserName = mainMenuViewModel.getLoggedInUser();
                shareWatchlistState.setLoggedUserName(loggedUserName);
                shareWatchlistViewModel.setState(shareWatchlistState);

                // change to ShareWatchlist View
                viewManagerModel.setActiveView(shareWatchlistViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });
    }


}
