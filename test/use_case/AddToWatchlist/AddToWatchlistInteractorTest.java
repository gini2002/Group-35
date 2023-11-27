package use_case.AddToWatchlist;

import data_access.AddToWatchlistDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import org.junit.Test;
import usecase_adaptor.AddToWatchlist.AddToWatchlistPresenter;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;

import static org.junit.Assert.*;

public class AddToWatchlistInteractorTest {

    @Test
    public void successTest() {
        AddToWatchlistDataAccessInterface DAO = new AddToWatchlistDataAccessObject("testFile", new CommonUserFactory());
        //TODO another DAO save user: new User("user")

        AddToWatchlistViewModel viewModel = new AddToWatchlistViewModel();
        AddToWatchlistOutputBoundary successPresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void PrepareFailView(String error) {
                fail("not expected");
            }

            @Override
            public void PrepareSuccessView(AddToWatchlistOutputData outputData) {
                assertEquals("name", outputData.getMovieName());
                Movie movie = new Movie("name", 1);
                assertTrue(DAO.getUser("user").getWatchlist().contains(movie));
            }
        };

        Movie movie = new Movie("name", 1);
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(movie, "user");

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(successPresenter,DAO);
        interactor.execute(inputData);
    }

    @Test
    public void failTest() {
        AddToWatchlistDataAccessInterface DAO = new AddToWatchlistDataAccessObject("testFile", new CommonUserFactory());
        //TODO another DAO save user: new User("user") and watchlist have movie

        AddToWatchlistViewModel viewModel = new AddToWatchlistViewModel();
        AddToWatchlistOutputBoundary successPresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void PrepareFailView(String error) {
                assertEquals("name already exists", error);
            }

            @Override
            public void PrepareSuccessView(AddToWatchlistOutputData outputData) {
                fail("not expected");
            }
        };

        Movie movie = new Movie("name", 1);
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(movie, "user");

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(successPresenter,DAO);
        interactor.execute(inputData);

    }
}