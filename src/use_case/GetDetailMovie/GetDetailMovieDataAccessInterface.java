package use_case.GetDetailMovie;

import entity.Movie;

import java.io.IOException;

public interface GetDetailMovieDataAccessInterface {
    Movie getdetailMovie(int id);

    boolean existsById(int id);
}
