package use_case.ShareWatchlist;

import data_access.ShareWatchlistDataAccessObject;
import entity.CommonUserFactory;
import entity.Movie;
import entity.Watchlist;
import org.junit.jupiter.api.Test;
import usecase_adaptor.ShareWatchlist.ShareWatchlistPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShareWatchlistInteractorTest {

    @Test
    public void successTest() {

        ShareWatchlistDataAccessInterface DAO = new ShareWatchlistDataAccessObject("ShareTestFile", new CommonUserFactory());

        //TODO add users "sender" and "receiver
        Movie movie = new Movie("name", 1);
        //TODO sender add movie to watchlist

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
                assertEquals(list, DAO.getUser("receiver").getSharedWatchlist("sender"));
            }
        };

        ShareWatchlistInputData inputData = new ShareWatchlistInputData("sender", "receiver");

        ShareWatchlistInputBoundary interactor = new ShareWatchlistInteractor(presenter, DAO);
        interactor.execute(inputData);
    }

    @Test
    public void UserNotExistFailTest() {
        ShareWatchlistDataAccessInterface DAO = new ShareWatchlistDataAccessObject("ShareTestFile", new CommonUserFactory());

        //TODO add users "sender"
        Movie movie = new Movie("name", 1);
        //TODO sender add movie to watchlist

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
        ShareWatchlistDataAccessInterface DAO = new ShareWatchlistDataAccessObject("ShareTestFile", new CommonUserFactory());

        //TODO add users "sender" and "receiver"

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