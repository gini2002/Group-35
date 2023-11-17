package usecase_adaptor.MovieSearchByKeyword;

import java.util.List;
import entity.Movie;

public class SearchByNameState {

    private List<Movie> movies;

    public SearchByNameState() {}

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
