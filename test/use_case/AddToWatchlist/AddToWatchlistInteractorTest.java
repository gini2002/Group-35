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
import java.util.List;

import static org.junit.Assert.*;

public class AddToWatchlistInteractorTest {

    @Test
    public void successTest() {

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./AddTestFile1.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = new CommonUserFactory().create("user",
                "password", LocalDateTime.of(1,1,1,1,1));
        userDataAccessObject.save(user);

        AddToWatchlistDataAccessInterface DAO = new AddToWatchlistDataAccessObject("./AddTestFile1.csv", new CommonUserFactory());

        AddToWatchlistOutputBoundary successPresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void PrepareFailView(String error) {
                fail("not expected");
            }

            @Override
            public void PrepareSuccessView(AddToWatchlistOutputData outputData) {
                assertEquals("Arial", outputData.getMovieName());
                List<Movie> watchlist= DAO.getUser("user").getWatchlist();
                int contain = 0;
                for (Movie movies:watchlist) {
                    if (movies.getID() == 2) {
                        contain += 1;
                    }
                }
                assertEquals(1, contain);
            }
        };

        Movie movie = new Movie("Arial", 2);
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(movie, "user");

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(successPresenter,DAO);
        interactor.execute(inputData);
    }

    @Test
    public void failTest() {

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./AddTestFile2.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        User user = new CommonUserFactory().create("user",
                "password", LocalDateTime.of(1,1,1,1,1));
        user.addMovieToWatchlist(new Movie("Arial", 2));
        userDataAccessObject.save(user);

        AddToWatchlistDataAccessInterface DAO = new AddToWatchlistDataAccessObject("./AddTestFile2.csv", new CommonUserFactory());

        AddToWatchlistViewModel viewModel = new AddToWatchlistViewModel();
        AddToWatchlistOutputBoundary failPresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void PrepareFailView(String error) {
                assertEquals("Arial already exists", error);
            }

            @Override
            public void PrepareSuccessView(AddToWatchlistOutputData outputData) {
                fail("not expected");
            }
        };

        Movie movie = new Movie("Arial", 2);
        AddToWatchlistInputData inputData = new AddToWatchlistInputData(movie, "user");

        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(failPresenter,DAO);
        interactor.execute(inputData);

    }
}