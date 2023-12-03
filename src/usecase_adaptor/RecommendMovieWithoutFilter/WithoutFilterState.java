package usecase_adaptor.RecommendMovieWithoutFilter;

import entity.Movie;

import java.util.List;

public class WithoutFilterState {

    private List<Movie> movies;
    private String username;

    public WithoutFilterState() {}

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}

