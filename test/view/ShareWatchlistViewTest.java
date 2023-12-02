package view;

import app.Main;
import app.ShareWatchlistUseCaseFactory;
import data_access.FileUserDataAccessObject;
import data_access.ShareWatchlistDataAccessObject;
import data_access.WatchlistDAO;
import data_access.WithoutFilterDAO;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.ShareWatchlist.ShareWatchlistDataAccessInterface;
import use_case.ShareWatchlist.ShareWatchlistInputBoundary;
import use_case.signup.SignupInputBoundary;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ShareWatchlist.ShareWatchlistController;
import usecase_adaptor.ShareWatchlist.ShareWatchlistViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class ShareWatchlistViewTest {

    static String message = "";
    static boolean popUpDiscovered = false;

    public void addTwoUsers() {
        UserFactory userFactory = new CommonUserFactory();
        FileUserDataAccessObject fileDAO;
        try {
            fileDAO = new FileUserDataAccessObject("./ShareViewTest.csv", userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User sender = userFactory.create("se", "password1", LocalDateTime.now());
        Movie movie = new Movie("Arial", 2);
        sender.addMovieToWatchlist(movie);
        User receiver = userFactory.create("re", "password2", LocalDateTime.now());

        fileDAO.save(sender);
        System.out.println("1");
        fileDAO.save(receiver);
    }

    public JButton getButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        ShareWatchlistView sv = (ShareWatchlistView) jp2.getComponent(3);

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(0); // this should be the clear button
    }

    public JTextField getTextField(int a, int b, int c) {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        ShareWatchlistView sv = (ShareWatchlistView) jp2.getComponent(a);
        JPanel keywordPanel = (JPanel) sv.getComponent(b);

        return (JTextField) keywordPanel.getComponent(c); // this should be the clear button
    }

    @Test
    public void testShareButtonExist() throws FileNotFoundException, WithoutFilterDAO.NoDataException, WatchlistDAO.NoDataException {
        Main.main(null);
        JButton button = getButton();
        assertEquals("share with user", button.getText());
    }

    @Test
    public void testTextFieldExist() throws FileNotFoundException, WithoutFilterDAO.NoDataException, WatchlistDAO.NoDataException {
        Main.main(null);
        JTextField textField = getTextField(3, 1, 1);
        assertEquals("", textField.getText());
    }

    @Test
    public void testShareSignupView() {

        addTwoUsers();

        ShareWatchlistViewModel viewModel = new ShareWatchlistViewModel();
        viewModel.getState().setLoggedUserName("se");
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        ShareWatchlistDataAccessInterface shareWatchlistDataAccessObject;
        shareWatchlistDataAccessObject = new ShareWatchlistDataAccessObject(
                "./ShareViewTest.csv",
                new CommonUserFactory());
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        ShareWatchlistView shareView = ShareWatchlistUseCaseFactory.create(
                viewManagerModel, viewModel, shareWatchlistDataAccessObject, mainMenuViewModel);

        JFrame jf = new JFrame();
        jf.setContentPane(shareView);
        jf.pack();
        jf.setVisible(true);

        // get field
        JPanel panelText = (JPanel) shareView.getComponent(1);
        JTextField textField = (JTextField) panelText.getComponent(1);

        // type name in field
        KeyEvent event = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, 'e');

        panelText.dispatchEvent(event);

        KeyEvent event2 = new KeyEvent(textField, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                0, KeyEvent.VK_UNDEFINED, 'r');

        panelText.dispatchEvent(event2);


        // get button
        JPanel panelButton = (JPanel) shareView.getComponent(2);
        JButton button = (JButton) panelButton.getComponent(0);

        // click button
        button.doClick();

        assertEquals("re", viewModel.getState().getReseiverName());
    }
}