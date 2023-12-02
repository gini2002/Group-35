package use_case.GetDetailMovie;
import data_access.MovieDetailAccessAPI;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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
                ArrayList<String> genres = new ArrayList<>();
                genres.add("Adventure"); genres.add("Action");genres.add("Science Fiction");
                assertEquals(11, response.getId());
                assertEquals("Star Wars", response.getTitle());
                assertEquals("user1", response.getLoggedinusername());
                assertEquals("Princess Leia is captured and held hostage " +
                        "by the evil Imperial forces in their effort to take over the galactic Empire. " +
                        "Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable " +
                        "robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace " +
                        "and justice in the Empire.", response.getOverview());
                assertEquals("/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg", response.getPoster_path());
                assertEquals(genres, response.getGenre());
            }
        };
        GetDetailMovieInputData getDetailMovieInputData = new GetDetailMovieInputData(11, "user1");
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
        GetDetailMovieInputData getDetailMovieInputData = new GetDetailMovieInputData(30, "user1");
        GetDetailMovieInputBoundary interactor = new GetDetailMovieInteractor(failPresenter, getDetailMovieRepo);
        interactor.execute(getDetailMovieInputData);
    }
}
