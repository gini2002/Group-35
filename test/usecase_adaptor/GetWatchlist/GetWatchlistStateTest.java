package usecase_adaptor.GetWatchlist;

import org.junit.Test;
import use_case.GetDetailMovie.GetDetailMovieInputBoundary;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieState;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GetWatchlistStateTest {
    @Test
    public void getWatchlistStateTest(){
        GetWatchListState state = new GetWatchListState();
        state.setLoggedinusername("user1");
        ArrayList<String> posters = new ArrayList<>();
        posters.add("url1"); posters.add("url2"); posters.add("url3");
        state.setWatchlistPosters(posters);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1); ids.add(2); ids.add(3);
        state.setIds(ids);
        state.setGetWatchListError("error");
        ArrayList<String> names = new ArrayList<>();
        names.add("movie1"); names.add("movie2"); names.add("movie3");
        state.setWatchlistNames(names);
        GetWatchListState state2 = new GetWatchListState(state);
        assertEquals("user1", state2.getLoggedinusername());
        assertEquals(posters, state2.getPosters());
        assertEquals(ids, state2.getIds());
        assertEquals(names, state2.getWatchlistNames());
        assertEquals("error", state2.getGetWatchListError());
    }
}
