package usecase_adaptor.MovieSearchByKeyword;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.Movie;

public class MovieResultViewModel {
    public final String TITLE_LABEL = "Movie Results View";

    public static final String MAIN_MENU_LABEL = "Back to Main Menu";

    private List<Movie> recommendedMovies;


    private String error;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

//    public List<Movie> getRecommendedMovies() {
//        return recommendedMovies;
//    }

    public String[] getRecommendedMovies() {
        List<String> movies = new ArrayList<>();
        if (this.recommendedMovies != null) {
            for (Movie movie : this.recommendedMovies) {
                movies.add(movie.getName());
            }
        }
        System.out.println("Called in result view model: " + movies);
        return movies.toArray(new String[0]);
    }

    public void setRecommendedMovies(List<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;

//        "recommendedMovies"
    }

    public String getError() {
        return error;
    }

//    public void setError(String error) {
//        this.error = error;
//        firePropertyChanged("error");
//    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    //    public void firePropertyChanged(String propertyName) {
//        support.firePropertyChange(propertyName, null, propertyName);
//    }
    public void firePropertyChanged() {
        support.firePropertyChange("recommendedMovies", null, this.recommendedMovies);
    }
}