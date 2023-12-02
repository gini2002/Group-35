package usecase_adaptor.MovieSearchByKeyword;

import java.util.List;
import entity.Movie;

public class SearchByNameState {

    private List<Movie> movies;
    private String username;

    public SearchByNameState() {}

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
