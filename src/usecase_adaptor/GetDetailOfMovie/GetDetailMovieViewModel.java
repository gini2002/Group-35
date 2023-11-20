package usecase_adaptor.GetDetailOfMovie;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GetDetailMovieViewModel extends ViewModel {
    private String title;
    private String overview;
    private List<String> genre;
    private List<String> actors;
    private String poster_path;
    public static final String DELETE_WATCHLIST_MOVIE_LABEL = "Remove From Watchlist";
    public static final String ADD_WATCH_LIST_BUTTON_LABEL = "Add to watchlist";

    private GetDetailMovieState getDetailMovieState = new GetDetailMovieState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GetDetailMovieViewModel(){
        super("Details");
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public void setGenre(List<String> genre){
        this.genre = genre;
    }

    public void setActors(List<String> actors){
        this.actors = actors;
    }

    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.getDetailMovieState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setGetDetailMovieState(GetDetailMovieState getDetailMovieState) {
        this.getDetailMovieState = getDetailMovieState;
    }

    public GetDetailMovieState getGetDetailMovieState() {
        return getDetailMovieState;
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }
}