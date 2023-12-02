package use_case.GetWatchList;

import data_access.AddToWatchlistDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
                ArrayList<String> names = new ArrayList<>();
                ArrayList<String> poster_urls = new ArrayList<>();
                ids.add(2); names.add("Ariel");poster_urls.add("/ojDg0PGvs6R9xYFodRct2kdI6wC.jpg");
                assertEquals(ids, getWatchListOutputData.getIds());
                assertEquals(names, getWatchListOutputData.getNames());
                assertEquals(poster_urls, getWatchListOutputData.getPoster_urls());
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
