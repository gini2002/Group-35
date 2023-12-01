package view;

import app.ShareWatchlistUseCaseFactory;
import use_case.ShareWatchlist.ShareWatchlistInputBoundary;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistController;
import usecase_adaptor.ShareWatchlist.ShareWatchlistState;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShareWatchlistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "ShareWatchlist";

    private final ShareWatchlistViewModel shareWatchlistViewModel;

    private final JTextField sharedUsernameInputField = new JTextField(15);

    final JButton sharedUsernameButton;

    final JButton backButton;

    final JLabel shareUsernameLabel = new JLabel(ShareWatchlistViewModel.USER_NAME_LABEL);

    private final ShareWatchlistController shareWatchlistController;

    private final ViewManagerModel viewManagerModel;

    private final MainMenuViewModel mainMenuViewModel;

    public ShareWatchlistView(ShareWatchlistViewModel shareWatchlistViewModel,
                              ShareWatchlistController shareWatchlistController,
                              ViewManagerModel viewManagerModel,
                              MainMenuViewModel mainMenuViewModel) {
        this.shareWatchlistController = shareWatchlistController;
        this.shareWatchlistViewModel = shareWatchlistViewModel;
        this.shareWatchlistViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;

        JLabel title = new JLabel("Share Watchlist screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        sharedUsernameButton = new JButton(ShareWatchlistViewModel.USER_NAME_LABEL);
        buttons.add(sharedUsernameButton);
        backButton = new JButton(ShareWatchlistViewModel.BACK_LABEL);
        buttons.add(backButton);

        JPanel keywordPanel = new JPanel();
        keywordPanel.add(new JLabel("Enter username here:"));
        keywordPanel.add(sharedUsernameInputField);

        sharedUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(sharedUsernameButton)) {
                    String currentUser = shareWatchlistViewModel.getState().getLoggedUserName();
                    String sharedUser = sharedUsernameInputField.getText();
                    shareWatchlistController.execute(currentUser, sharedUser);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backButton)) {
                    viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        this.add(title);
        this.add(keywordPanel);
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("ShareWatchlistState")) {
            ShareWatchlistState state = (ShareWatchlistState) evt.getNewValue();
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
            } else {
                JOptionPane.showMessageDialog(this, "shared with " + state.getReseiverName());
            }
        }
    }
}