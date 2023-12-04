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
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistController;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class GetDetailMovieViewTest {
    @Test
    public void testGetDetailMovieView()  {
        GetDetailMovieViewModel getDetailMovieViewModel = mock(GetDetailMovieViewModel.class);
        AddToWatchlistViewModel addToWatchlistViewModel = mock(AddToWatchlistViewModel.class);
        DeleteWatchlistViewModel deleteWatchlistViewModel = mock(DeleteWatchlistViewModel.class);
        MainMenuViewModel mainMenuViewModel = mock(MainMenuViewModel.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        AddToWatchlistController addToWatchlistController = mock(AddToWatchlistController.class);
        DeleteWatchlistController deleteWatchlistController = mock(DeleteWatchlistController.class);
        // AddToWatchlistDataAccessInterface DAO2 = new AddToWatchlistDataAccessObject("./AddTestFile1.csv",
          //      new CommonUserFactory());
        // DeleteWatchlistDataAccessInterface DAO = new WatchlistDAO("./AddTestFile1.csv", new CommonUserFactory());
        GetDetailMovieView getDetailMovieView = new GetDetailMovieView(getDetailMovieViewModel,
                addToWatchlistController, addToWatchlistViewModel,deleteWatchlistController, deleteWatchlistViewModel,
                viewManagerModel, mainMenuViewModel);

        assertEquals("" ,getDetailMovieView.movie_title.getText());
        assertEquals("", getDetailMovieView.overview.getText());
        assertEquals("", getDetailMovieView.genre.getText());

        getDetailMovieView.backToMainMenu.doClick();
        verify(mainMenuViewModel, times(1)).getViewName();

        getDetailMovieView.updateView();
        getDetailMovieView.backToMainMenu.doClick();
        verify(viewManagerModel, times(1)).setActiveView(any());

        getDetailMovieView.propertyChange(new PropertyChangeEvent(this, "state", null,
                getDetailMovieViewModel.getGetDetailMovieState()));
}}


