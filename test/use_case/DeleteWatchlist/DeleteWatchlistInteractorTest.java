package use_case.DeleteWatchlist;

import data_access.WatchlistDAO;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistPresenter;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistViewModel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.kotlin.VerificationKt.verify;

public class DeleteWatchlistInteractorTest {

    @Test
    public void successTest() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./DeleteTestFile1.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User user = new CommonUserFactory().create("user", "password", LocalDateTime.of(1,1,1,1,1));
        Movie movie = new Movie("Arial", 2);
        user.addMovieToWatchlist(movie);
        userDataAccessObject.save(user);

        DeleteWatchlistDataAccessInterface DAO = new WatchlistDAO("./DeleteTestFile1.csv", new CommonUserFactory());

        DeleteWatchlistOutputBoundary successPresenter = new DeleteWatchlistOutputBoundary() {
            @Override
            public void PrepareFailView(String error) {
                fail("not expected");
            }

            @Override
            public void PrepareSuccessView(DeleteWatchlistOutputData outputData) {
                assertEquals("Arial", outputData.getMovieName());
                DeleteWatchlistDataAccessInterface DAO2 = new WatchlistDAO(
                        DAO.getPath(), new CommonUserFactory());
                List<Movie> watchlist = DAO2.getUser("user").getWatchlist();
                boolean contains = watchlist.stream().anyMatch(m -> m.getID() == 2);
                assertFalse(contains);
            }
        };

        DeleteWatchlistInputData inputData = new DeleteWatchlistInputData(movie, "user");
        DeleteWatchlistInputBoundary interactor = new DeleteWatchlistInteractor(successPresenter, DAO);
        interactor.execute(inputData);
    }

    @Test
    public void failTestMovieNotInWatchlist() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./DeleteTestFile2.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User user = new CommonUserFactory().create("user", "password", LocalDateTime.of(1,1,1,1,1));
        userDataAccessObject.save(user);

        DeleteWatchlistDataAccessInterface DAO = new WatchlistDAO("./DeleteTestFile2.csv", new CommonUserFactory());

        DeleteWatchlistViewModel viewModel = new DeleteWatchlistViewModel();
        DeleteWatchlistOutputBoundary failPresenter = new DeleteWatchlistOutputBoundary() {
            @Override
            public void PrepareFailView(String error) {
                assertEquals("Arial doesn't exist", error);
            }

            @Override
            public void PrepareSuccessView(DeleteWatchlistOutputData outputData) {
                fail("not expected");
            }
        };

        Movie movie = new Movie("Arial", 2);
        DeleteWatchlistInputData inputData = new DeleteWatchlistInputData(movie, "user");
        DeleteWatchlistInputBoundary interactor = new DeleteWatchlistInteractor(failPresenter, DAO);
        interactor.execute(inputData);
    }
}
