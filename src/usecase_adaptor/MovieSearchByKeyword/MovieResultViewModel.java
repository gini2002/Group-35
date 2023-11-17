package usecase_adaptor.MovieSearchByKeyword;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import entity.Movie;

public class MovieResultViewModel {
    public final String TITLE_LABEL = "Movie Results View";

    private List<Movie> recommendedMovies;


    private String error;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

//    public List<Movie> getRecommendedMovies() {
//        return recommendedMovies;
//    }

    public List<String> getRecommendedMovies() {
        List<String> movies = new ArrayList<>();
        if (recommendedMovies != null) {
            for (Movie movie : recommendedMovies) {
                movies.add(movie.getName());
            }
        }
        return movies;
    }

    public void setRecommendedMovies(List<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;

        firePropertyChanged();
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
