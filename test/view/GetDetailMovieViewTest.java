package view;

import app.GetDetailOfMovieUseCaseFactory;
import app.Main;
import data_access.AddToWatchlistDataAccessObject;
import data_access.MovieDetailAccessAPI;
import data_access.WatchlistDAO;
import data_access.WithoutFilterDAO;
import entity.CommonUserFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import use_case.AddToWatchlist.AddToWatchlistDataAccessInterface;
import use_case.DeleteWatchlist.DeleteWatchlistDataAccessInterface;
import use_case.GetDetailMovie.GetDetailMovieDataAccessInterface;
import use_case.GetDetailMovie.GetDetailMovieInputBoundary;
import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistViewModel;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowStateListener;
import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetDetailMovieViewTest {
    public JButton getButton(){
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        GetDetailMovieView getDetailMovieView = (GetDetailMovieView) jp2.getComponent(3);

        JPanel buttons = (JPanel) getDetailMovieView.getComponent(2);

        return (JButton) buttons.getComponent(0);
    }

    @Test
    public void testGetDetailButtonExist() throws FileNotFoundException, WatchlistDAO.NoDataException, WithoutFilterDAO.NoDataException {
        Main.main(null);
        JButton button = getButton();
        assertEquals("", button.getText());
    }

    @Test
    public void testGetDetailMovieView() throws WatchlistDAO.NoDataException {
        GetDetailMovieViewModel getDetailMovieViewModel = new GetDetailMovieViewModel();
        GetDetailMovieDataAccessInterface getDetailMovieDAO = new MovieDetailAccessAPI();
        AddToWatchlistViewModel addToWatchlistViewModel = new AddToWatchlistViewModel();
        DeleteWatchlistViewModel deleteWatchlistViewModel = new DeleteWatchlistViewModel();
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AddToWatchlistDataAccessInterface DAO2 = new AddToWatchlistDataAccessObject("./AddTestFile1.csv",
                new CommonUserFactory());
        DeleteWatchlistDataAccessInterface DAO = new WatchlistDAO("./AddTestFile1.csv");
        GetDetailMovieView getDetailMovieView = GetDetailOfMovieUseCaseFactory.create(getDetailMovieViewModel,
                DAO2, addToWatchlistViewModel, DAO, deleteWatchlistViewModel, viewManagerModel, mainMenuViewModel);

        JFrame jf = new JFrame();
        jf.setContentPane(getDetailMovieView);
        jf.pack();
        jf.setVisible(true);

}}


