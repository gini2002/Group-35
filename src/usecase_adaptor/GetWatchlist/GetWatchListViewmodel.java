package usecase_adaptor.GetWatchlist;

import entity.Movie;
import usecase_adaptor.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GetWatchListViewmodel extends ViewModel {

    private List<String> names;

    private List<String> posters;

    private String error;

    public static final String DETAIL_MOVIE_LABEL = "";

    public static final String MAIN_MENU_BUTTON_LABEL = "BACK TO MAIN MENU";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private GetWatchListState state = new GetWatchListState();

    public GetWatchListViewmodel() {
        super("Watchlist");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GetWatchListState getState(){
        return state;
    }

    public void setState(GetWatchListState state) {
        this.state = state;
    }


    public void setWatchlist(List<String> names, List<String> posters){
        this.names = names;
        this.posters = posters;
        firePropertyChanged();
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getPosters() {
        return posters;
    }
}
