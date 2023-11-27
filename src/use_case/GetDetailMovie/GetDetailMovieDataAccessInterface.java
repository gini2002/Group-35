package use_case.GetDetailMovie;

import entity.Movie;

public interface GetDetailMovieDataAccessInterface {
    Movie getdetailMovie(int id);

    boolean existsById(int id);
}
