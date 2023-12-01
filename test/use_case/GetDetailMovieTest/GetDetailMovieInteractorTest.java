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
                assertEquals("Star Wars", response.getTitle());
                assertEquals("user1", response.getLoggedinusername());
            }
        };
        GetDetailMovieInputData getDetailMovieInputData = new GetDetailMovieInputData("Star Wars",
                11, "user1");
        GetDetailMovieInputBoundary interactor = new GetDetailMovieInteractor(successPresenter, getDetailMovieRepo);
        interactor.execute(getDetailMovieInputData);
    }

    @Test
    public void notfoundTest() {
        GetDetailMovieDataAccessInterface getDetailMovieRepo = new MovieDetailAccessAPI();
        GetDetailMovieOutputBoundary failPresenter = new GetDetailMovieOutputBoundary() {
            @Override
            public void getDetailMovieFailView(String error) {
                assertEquals("The resource you requested could not be found.", error);
            }

            @Override
            public void getDetailMovieSuccessView(GetDetailMovieOutputData response) {
                fail("should be wrong");
            }
        };
        GetDetailMovieInputData getDetailMovieInputData = new GetDetailMovieInputData("no such movie",
                30, "user1");
        GetDetailMovieInputBoundary interactor = new GetDetailMovieInteractor(failPresenter, getDetailMovieRepo);
        interactor.execute(getDetailMovieInputData);
    }
}
