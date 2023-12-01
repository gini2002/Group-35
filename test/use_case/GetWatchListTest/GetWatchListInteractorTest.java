package use_case.GetWatchListTest;

import data_access.GetWatchListDAO;
import org.junit.Test;
import use_case.GetWatchList.*;
import usecase_adaptor.GetWatchlist.GetWatchlistPresenter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetWatchListInteractorTest {
    @Test
    public void successTest() throws FileNotFoundException {
        // TODO: exception?
        GetWatchListDataAccessInterface getWatchListRepo = new GetWatchListDAO("./testusertowatchlist.csv");
        GetWatchListOutputBoundary successPresenter = new GetWatchListOutputBoundary() {
            @Override
            public void getWatchlistSuccessView(GetWatchListOutputData getWatchListOutputData) {
                assertEquals("user1", getWatchListOutputData.getLogged_in_username());
                assertEquals(new ArrayList<>(), getWatchListOutputData.getIds());
                assertEquals(new ArrayList<>(), getWatchListOutputData.getNames());
                assertEquals(new ArrayList<>(), getWatchListOutputData.getPoster_urls());
                // TODO: Need add to watchlist tp test non-empty ones
            }

            @Override
            public void getWatchlistFailView(String error) {
                fail("sth went wrong.");
            }
        };
        GetWatchListInputData getWatchListInputData = new GetWatchListInputData("user1");
        GetWatchListInputBoundary interactor = new GetWatchListInteractor(getWatchListRepo, successPresenter);
        interactor.execute(getWatchListInputData);
    }
}
