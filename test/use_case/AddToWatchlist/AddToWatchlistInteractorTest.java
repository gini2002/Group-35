package use_case.AddToWatchlist;

import data_access.AddToWatchlistDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import org.junit.Test;
import usecase_adaptor.AddToWatchlist.AddToWatchlistPresenter;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AddToWatchlistInteractorTest {

    @Test
    public void successTest() {
        AddToWatchlistDataAccessInterface DAO = new AddToWatchlistDataAccessObject("./AddTestFile1.csv", new CommonUserFactory());

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./AddTestFile1.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1,1,1,1,1));
        userDataAccessObject.save(user);

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
        AddToWatchlistDataAccessInterface DAO = new AddToWatchlistDataAccessObject("./AddTestFile2.csv", new CommonUserFactory());

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./AddTestFile2.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        User user = new CommonUserFactory().create("name",
                "password", LocalDateTime.of(1,1,1,1,1));
        user.addMovieToWatchlist(new Movie("movie", 1));
        userDataAccessObject.save(user);



        AddToWatchlistViewModel viewModel = new AddToWatchlistViewModel();
        AddToWatchlistOutputBoundary failPresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void PrepareFailView(String error) {
                assertEquals("name already exists", error);
            }

            @Override
            public void PrepareSuccessView(AddToWatchlistOutputData outputData) {
                fail("not expected");
            }
        };

        Movie movie = new Movie("movie", 1);
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(movie, "name");

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(failPresenter,DAO);
        interactor.execute(inputData);

    }
}