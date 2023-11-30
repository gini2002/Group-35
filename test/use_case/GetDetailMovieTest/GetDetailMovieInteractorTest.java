package use_case.GetDetailMovieTest;
import data_access.MovieDetailAccessAPI;
import org.junit.Test;
import use_case.GetDetailMovie.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetDetailMovieInteractorTest {
    @Test
    public void successtTest() {
        GetDetailMovieDataAccessInterface getDetailMovieRepo = new MovieDetailAccessAPI();
        GetDetailMovieOutputBoundary successPresenter = new GetDetailMovieOutputBoundary() {
            @Override
            public void getDetailMovieFailView(String error) {
                fail("sth went wrong");
            }

            @Override
            public void getDetailMovieSuccessView(GetDetailMovieOutputData response) {
                assertEquals(11, response.getId());
                assertEquals("Star Wars Collection", response.getTitle());
                assertEquals("user1", response.getLoggedinusername());
            }
        };
        GetDetailMovieInputData getDetailMovieInputData = new GetDetailMovieInputData("Star Wars Collection",
                11, "user1");
        GetDetailMovieInputBoundary interactor = new GetDetailMovieInteractor(successPresenter, getDetailMovieRepo);
        interactor.execute(getDetailMovieInputData);
    }
}
