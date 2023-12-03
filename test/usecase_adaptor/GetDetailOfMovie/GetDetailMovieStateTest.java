package usecase_adaptor.GetDetailOfMovie;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GetDetailMovieStateTest {
    @Test
    public void getDetailMovieStateTest() {
        GetDetailMovieState state = new GetDetailMovieState();
        state.setLoggedinusername("user1");
        state.setError("error");
        state.setTitle("Star Wars");
        ArrayList<String> genres = new ArrayList<>();
        genres.add("adventure"); genres.add("Science fiction");
        state.setGenre(genres);
        state.setOverview("overview");
        state.setPoster_path("http://jpgsomething");
        LocalDate localdate = LocalDate.of(2022, 2, 22);
        state.setReleaseDate(localdate);
        state.setId(11);
        GetDetailMovieState state2 = new GetDetailMovieState(state);
        assertEquals("user1", state2.getLoggedinusername());
        assertEquals("error", state2.getError());
        assertEquals("Star Wars", state2.getTitle());
        assertEquals(genres, state2.getGenre());
        assertEquals("overview", state2.getOverview());
        assertEquals(11, state2.getId());
        assertEquals(localdate, state2.getReleaseDate());
        assertEquals("http://jpgsomething", state2.getPoster_path());
    }
}