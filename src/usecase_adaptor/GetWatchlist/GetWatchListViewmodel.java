package usecase_adaptor.GetWatchlist;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GetWatchListViewmodel extends ViewModel {

    private List<String> names;

    private List<String> posters;

    private List<Integer> ids;

    private String logged_in_username;

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


    public void setWatchlist(List<String> names, List<String> posters, List<Integer> ids){
        this.names = names;
        this.posters = posters;
        this.ids = ids;
        firePropertyChanged();
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getPosters() {
        return posters;
    }

    public void setLogged_in_username(String logged_in_username) {
        this.logged_in_username = logged_in_username;
    }

    public String getLogged_in_username() {
        return logged_in_username;
    }

    public List<Integer> getIds() {
        return ids;
    }
}
