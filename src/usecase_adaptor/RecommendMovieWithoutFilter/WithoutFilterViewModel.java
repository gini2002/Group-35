package error_cases.RecommendMovieWithoutFilter;

import entity.Movie;
import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class WithoutFilterViewModel extends ViewModel {

    public final String TITLE_LABEL = "Movie Recommendation View Without Filter";
    public final String KEYWORD_LABEL = "Enter your watchlist ID here";

    public static final String SEARCH_BUTTON_LABEL = "Search";

    private String watchlistidInput = "";
    private List<Movie> withoutFilterMovies;


    private String error = null;

    public WithoutFilterViewModel() {
        super("movie_recommendation");
    }


    public void setwatchlistidInput(String watchlistidInput) {
        this.watchlistidInput = watchlistidInput;
        firePropertyChanged();
    }

    public String getwatchlistidInput() {
        return watchlistidInput;
    }

    public String[] getRecommendedMovies() {
        List<String> movies = new ArrayList<>();
        if (this.withoutFilterMovies != null) {
            for (Movie movie : this.withoutFilterMovies) {
                movies.add(movie.getName());
            }
        }
        System.out.println("Called in result view model: " + movies);
        return movies.toArray(new String[0]);
    }

    public void setWithoutFilterMovies(List<Movie> withoutFilterMovies) {
        this.withoutFilterMovies = withoutFilterMovies;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }



    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


//    public void addErrorPropertyChangeListener(PropertyChangeListener listener) {
//        errorSupport.addPropertyChangeListener(listener);
//    }

    // This is what the MovieRecommendationPresenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("watchlistidInput", null, this.watchlistidInput);
    }

    public void fireRecommendedMoviesChanged() {
        support.firePropertyChange("withoutFilterMovies", null, watchlistidInput);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
//    public void addErrorPropertyChangeListener(PropertyChangeListener listener) {
//        errorSupport.addPropertyChangeListener(listener);
//    }
//    private void fireErrorChanged() {
//        errorSupport.firePropertyChange("error", null, error);
//    }
}
