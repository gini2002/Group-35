package use_case.GetDetailMovie;

import entity.Movie;

public interface GetDetailMovieDataAccessInterface {
    Movie getByName(String name);

    boolean existsByName(String name);
}
