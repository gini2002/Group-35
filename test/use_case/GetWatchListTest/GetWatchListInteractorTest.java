package use_case.GetWatchListTest;

import data_access.AddToWatchlistDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.GetWatchListDAO;
import entity.CommonUserFactory;
import entity.Movie;
import entity.User;
import org.junit.Test;
import use_case.AddToWatchlist.*;
import use_case.GetWatchList.*;
import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.GetWatchlist.GetWatchlistPresenter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetWatchListInteractorTest {
    @Test
    public void successTest() throws FileNotFoundException {
        GetWatchListDataAccessInterface getWatchListRepo = new AddToWatchlistDataAccessObject("./AddTestFile1.csv", new CommonUserFactory());

        GetWatchListOutputBoundary successPresenter2 = new GetWatchListOutputBoundary() {
            @Override
            public void getWatchlistSuccessView(GetWatchListOutputData getWatchListOutputData) {
                assertEquals("user", getWatchListOutputData.getLogged_in_username());
                ArrayList<Integer> ids = new ArrayList<>();
                ids.add(2);ids.add(2);ids.add(2);ids.add(2);
                assertEquals(ids, getWatchListOutputData.getIds());
                assertEquals(new ArrayList<>(), getWatchListOutputData.getNames());
                assertEquals(new ArrayList<>(), getWatchListOutputData.getPoster_urls());
                // TODO: Need add to watchlist tp test non-empty ones
            }

            @Override
            public void getWatchlistFailView(String error) {
                fail("sth went wrong.");
            }
        };
        GetWatchListInputData getWatchListInputData = new GetWatchListInputData("user");
        GetWatchListInputBoundary interactor2 = new GetWatchListInteractor(getWatchListRepo, successPresenter2);
        interactor2.execute(getWatchListInputData);
    }
}
