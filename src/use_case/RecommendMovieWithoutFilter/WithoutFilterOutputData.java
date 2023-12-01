package use_case.RecommendMovieWithoutFilter;

import entity.Movie;

import java.util.List;

public class WithoutFilterOutputData {

    private final List<Movie> withoutFilterMovies;
    private boolean useCaseFailed;

    public WithoutFilterOutputData(List<Movie> withoutFilterMovies) {
        this.withoutFilterMovies = withoutFilterMovies;
    }


    //getRecommendedMovies
    public List<Movie> getWithoutFilterMovies() {
        System.out.println(withoutFilterMovies);
        return withoutFilterMovies;
    }

}
