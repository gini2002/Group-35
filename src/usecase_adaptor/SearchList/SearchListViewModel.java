package usecase_adaptor.SearchList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.Movie;
import usecase_adaptor.ViewModel;

public class SearchListViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search list view";
    public static final String MAIN_MENU_LABEL = "Back to Main Menu";

    private List<Movie> searchList;


    private String error;

    public SearchListViewModel() {
        super("movie_recommendation");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);



    public String[] getSearchList() {
        List<String> movies = new ArrayList<>();
        if (this.searchList != null) {
            for (Movie movie : this.searchList) {
                movies.add(movie.getName());
            }
        }
        return movies.toArray(new String[0]);
    }

    public void setSearchList(List<Movie> recommendedMovies) {
        if (searchList == null) {
            searchList = new ArrayList<>();
        }
        searchList.addAll(recommendedMovies);
    }

    public String getError() {
        return error;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public void firePropertyChanged() {
        support.firePropertyChange("searchList", null, this.searchList);
    }
}
