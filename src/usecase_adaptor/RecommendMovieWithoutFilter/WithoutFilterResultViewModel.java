package usecase_adaptor.RecommendMovieWithoutFilter;

import entity.Movie;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
public class WithoutFilterResultViewModel {
    public final String TITLE_LABEL = "Movie Results View";

    private List<Movie> withoutFilterMovies;


    private String error;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


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
        support.firePropertyChange("withoutFilterMovies", null, this.withoutFilterMovies);
    }
}
