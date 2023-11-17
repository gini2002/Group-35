package usecase_adaptor.GetWatchlist;

import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;

public class GetWatchListViewmodel extends ViewModel {
    public static final String DELETE_WATCHLIST_MOVIE_LABEL = "Remove From Watchlist";

    public static final String BACK_LABEL = "Back";

    public GetWatchListViewmodel() {
        super("Watchlist");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}