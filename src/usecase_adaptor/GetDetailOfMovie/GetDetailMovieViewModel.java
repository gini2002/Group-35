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

    private GetDetailMovieStatus getDetailMovieStatus = new GetDetailMovieStatus();

    public static final String ADD_WATCH_LIST_BUTTON_LABEL = "add to watchlist";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GetDetailMovieViewModel(String title){
        super(title);
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

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
