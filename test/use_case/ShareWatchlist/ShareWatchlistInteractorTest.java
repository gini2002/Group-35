package use_case.ShareWatchlist;

import data_access.FileUserDataAccessObject;
import data_access.ShareWatchlistDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import entity.Watchlist;
import org.junit.jupiter.api.Test;
import usecase_adaptor.ShareWatchlist.ShareWatchlistPresenter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShareWatchlistInteractorTest {

    @Test
    public void successTest() {

        ShareWatchlistDataAccessInterface DAO = new ShareWatchlistDataAccessObject("./ShareTestFile1.csv", new CommonUserFactory());

        User sender = new CommonUserFactory().
                create("sender", "password",
                        LocalDateTime.of(1,1,1,1,1));
        User receiver = new CommonUserFactory().
                create("receiver", "password",
                        LocalDateTime.of(1,1,1,1,1));

        Movie movie = new Movie("name", 2);
        sender.addMovieToWatchlist(movie);

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./ShareTestFile1.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userDataAccessObject.save(sender);
        userDataAccessObject.save(receiver);

        ShareWatchlistOutputBoundary presenter = new ShareWatchlistOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                fail("not expected to be FailView");
            }

            @Override
            public void prepareSuccessView(ShareWatchlistOutputData outputData) {
                assertEquals("receiver", outputData.getUserName());
                List<Movie> list = new ArrayList<>();
                list.add(0, new Movie("name", 1));
                //assertEquals(list, DAO.getUser("receiver").getSharedWatchlist("sender"));
            }
        };

        ShareWatchlistInputData inputData = new ShareWatchlistInputData("sender", "receiver");

        ShareWatchlistInputBoundary interactor = new ShareWatchlistInteractor(presenter, DAO);
        interactor.execute(inputData);
    }

    @Test
    public void UserNotExistFailTest() {
        ShareWatchlistDataAccessInterface DAO = new ShareWatchlistDataAccessObject("./ShareTestFile2.csv", new CommonUserFactory());

        User sender = new CommonUserFactory().
                create("sender", "password",
                        LocalDateTime.of(1,1,1,1,1));
        Movie movie = new Movie("name", 1);
        sender.addMovieToWatchlist(movie);

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./ShareTestFile2.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userDataAccessObject.save(sender);

        ShareWatchlistOutputBoundary presenter = new ShareWatchlistOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals("the user does not exist", error);
            }

            @Override
            public void prepareSuccessView(ShareWatchlistOutputData outputData) {
                fail("not expected to be Success");
            }
        };

        ShareWatchlistInputData inputData = new ShareWatchlistInputData("sender", "receiver");

        ShareWatchlistInputBoundary interactor = new ShareWatchlistInteractor(presenter, DAO);
        interactor.execute(inputData);

    }

    @Test
    public void EmptyWatchlistFailTest() {
        ShareWatchlistDataAccessInterface DAO = new ShareWatchlistDataAccessObject("./ShareTestFile3.csv", new CommonUserFactory());

        User sender = new CommonUserFactory().
                create("sender", "password",
                        LocalDateTime.of(1,1,1,1,1));
        User receiver = new CommonUserFactory().
                create("receiver", "password",
                        LocalDateTime.of(1,1,1,1,1));

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./ShareTestFile3.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userDataAccessObject.save(sender);
        userDataAccessObject.save(receiver);

        ShareWatchlistOutputBoundary presenter = new ShareWatchlistOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals("the watchlist is empty", error);
            }

            @Override
            public void prepareSuccessView(ShareWatchlistOutputData outputData) {
                fail("not expected to be Success");
            }
        };

        ShareWatchlistInputData inputData = new ShareWatchlistInputData("sender", "receiver");

        ShareWatchlistInputBoundary interactor = new ShareWatchlistInteractor(presenter, DAO);
        interactor.execute(inputData);
    }

}