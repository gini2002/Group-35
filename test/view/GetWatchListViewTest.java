package view;

import app.GetWatchlistUseCaseFactory;
import data_access.GetWatchListDAO;
import data_access.MovieDetailAccessAPI;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.GetDetailMovie.GetDetailMovieDataAccessInterface;
import use_case.GetDetailMovie.GetDetailMovieInputBoundary;
import use_case.GetWatchList.GetWatchListDataAccessInterface;
import use_case.GetWatchList.GetWatchListInputBoundary;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieController;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.GetWatchlist.GetWatchlistController;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameController;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GetWatchListViewTest {
    @Test
    public void testGetWatchListView(){
        GetWatchListViewmodel getWatchListViewmodel = new GetWatchListViewmodel();
        GetDetailMovieViewModel getDetailMovieViewModel = new GetDetailMovieViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        GetWatchListDataAccessInterface getWatchListDataAccessInterface = new GetWatchListDAO("./AddTestFile1.csv");
        GetDetailMovieDataAccessInterface getDetailMovieDataAccessInterface = new MovieDetailAccessAPI();


        GetWatchlistView getWatchlistView = GetWatchlistUseCaseFactory.create(viewManagerModel,
                getWatchListDataAccessInterface, getDetailMovieViewModel,getWatchListViewmodel,
                getDetailMovieDataAccessInterface, mainMenuViewModel);
    }

    // @Test
    //    public void testUpdateView() {
    //        // Arrange
    //        SwingUtilities.invokeLater(() -> {
    //            // Creating mock objects
    //            GetWatchlistView getWatchlistView = Mockito.mock(GetWatchlistView.class);
    //            GetWatchListViewmodel getWatchlistViewModel = Mockito.mock(GetWatchListViewmodel.class);
    //            ViewManagerModel viewManagerModel = Mockito.mock(ViewManagerModel.class);
    //            GetDetailMovieController getDetailMovieController = Mockito.mock(GetDetailMovieController.class);
    //
    //            // Mocking the behavior of movieResultViewModel
    //            String[] recommendedMovies = {"Movie1", "Movie2", "Movie3"};
    //            when(getWatchlistViewModel.getNames()).thenReturn(new ArrayList<>());
    //
    //            // Creating a mock PropertyChangeEvent
    //            PropertyChangeEvent event = new PropertyChangeEvent(this, "state", null, recommendedMovies);
    //
    //            // Act
    //            getWatchlistView.propertyChange(event);
    //
    //            // Assert
    //        });
    //    }

    @Test
    public void testUpdateView() {
        GetWatchListViewmodel getWatchListViewmodel = mock(GetWatchListViewmodel.class);
        GetDetailMovieController getDetailMovieController = mock(GetDetailMovieController.class);
        GetWatchlistController controller = mock(GetWatchlistController.class);
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        MainMenuViewModel mainMenuViewModel = mock(MainMenuViewModel.class);
        GetDetailMovieViewModel getDetailMovieViewModel = mock(GetDetailMovieViewModel.class);
        GetWatchlistView getWatchlistView = new GetWatchlistView(getWatchListViewmodel,
                controller, getDetailMovieController, getDetailMovieViewModel, viewManagerModel, mainMenuViewModel);

        getWatchlistView.backToMainMenu.doClick();
        verify(mainMenuViewModel, times(1)).getViewName();

        getWatchlistView.updateView();
        verify(getWatchListViewmodel, times(1)).firePropertyChanged();

        String[] movies = {"Movie1", "Movie2", "Movie3"};
        when(getWatchListViewmodel.getNames()).thenReturn(List.of(movies));
        getWatchlistView.propertyChange(new PropertyChangeEvent(this, "state",
                null, getWatchListViewmodel.getNames()));

    }





        //GetWatchListViewmodel getWatchListViewmodel = new GetWatchListViewmodel();
        //            GetDetailMovieViewModel getDetailMovieViewModel = new GetDetailMovieViewModel();
        //            ViewManagerModel viewManagerModel = new ViewManagerModel();
        //            MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        //            GetWatchListDataAccessInterface getWatchListDataAccessInterface = new GetWatchListDAO("./AddTestFile1.csv");
        //            GetDetailMovieDataAccessInterface getDetailMovieDataAccessInterface = new MovieDetailAccessAPI();
        //
        //
        //            GetWatchlistView getWatchlistView = GetWatchlistUseCaseFactory.create(viewManagerModel,
        //                    getWatchListDataAccessInterface, getDetailMovieViewModel,getWatchListViewmodel,
        //                    getDetailMovieDataAccessInterface, mainMenuViewModel);
        //when(getWatchListViewmodel.getNames()).thenReturn(Arrays.asList("Movie1", "Movie2"));
        //        when(getWatchListViewmodel.getIds()).thenReturn(Arrays.asList(1, 2));
        //        when(getWatchListViewmodel.getLogged_in_username()).thenReturn("testUser");
        //
        //            // Update the view
        //        getWatchlistView.updateView();
        //
        //            // Verify that components are updated as expected
        //        assertEquals(2, getWatchlistView.getComponentCount()); // Assuming title, back button, and one movie button
        //        assertTrue(getWatchlistView.getComponent(0) instanceof JLabel); // Assuming the first component is a JLabel (title)
        //        assertTrue(getWatchlistView.getComponent(1) instanceof JPanel); // Assuming the second component is a JPanel (buttons)
        //            // Test other assertions based on your specific implementation
        //
        //            // Example: Test button action
        //        JButton backButton = (JButton) ((JPanel) getWatchlistView.getComponent(1)).getComponent(0);
        //        backButton.doClick();
        //        System.out.println(backButton.getName());
        //        verify(viewManagerModel, times(1)).setActiveView(anyString());
        //        verify(viewManagerModel, times(1)).firePropertyChanged();
        //        };


}
