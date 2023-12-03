package usecase_adaptor.RecommendMovieWithoutFilter;

import entity.Movie;
import view.WithoutFilterResultView;

import java.util.List;

public class WithoutFilterResultState {

    private String error;

    private List<Movie> withoutFilterMovies;

    public WithoutFilterResultState() {
    }

    public void setWithoutFilterMovies(List<Movie> movies) {
        this.withoutFilterMovies = movies;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Movie> getWithoutFilterMovies() {
        return withoutFilterMovies;
    }

    public String getError() {
        return error;
    }
}
