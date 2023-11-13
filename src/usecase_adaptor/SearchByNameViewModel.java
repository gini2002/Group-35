package usecase_adaptor;
//
//public class SearchByNameViewModel {
//    private SearchByNameState state = new SearchByNameState();
//
//    private String name = "SearchByName";
//
//    public SearchByNameViewModel() {
//    }
//
//    public void setState(SearchByNameState state) {
//        this.state = state;
//    }
//
//    public SearchByNameState getState() {
//        return state;
//    }
//}

import entity.Movie;
import usecase_adaptor.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SearchByNameViewModel extends ViewModel {

    public final String TITLE_LABEL = "Movie Recommendation View";
    public final String KEYWORD_LABEL = "Enter your keyword here";

    public static final String SEARCH_BUTTON_LABEL = "Search";

    private String keywordInput = "";
    private List<Movie> recommendedMovies;

    private String error = null;

    public SearchByNameViewModel() {
        super("movie_recommendation");
    }


    public void setKeywordInput(String keywordInput) {
        this.keywordInput = keywordInput;
        firePropertyChanged();
    }

    public String getKeywordInput() {
        return keywordInput;
    }

    public List<Movie> getRecommendedMovies() {
        return recommendedMovies;
    }

    public void setRecommendedMovies(List<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
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
        support.firePropertyChange("keywordInput", null, this.keywordInput);
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
