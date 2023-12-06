package use_case.GetDetailMovie;

import entity.Movie;

import java.io.IOException;
/**
 * The GetDetailMovieDataAccessInterface defines the data access to get detail of movie use case.
 */
public interface GetDetailMovieDataAccessInterface {
    /**
     * Retrieves a movie with detail we need and input movie id.
     *
     * @param id The id of movie we get detail for.
     * @return A movie with details stored in it.
     */
    Movie getdetailMovie(int id);

    /**
     * Return if the movie we get detail for exists.
     *
     * @param id The id of movie we get detail for.
     * @return True if the movie we search exists.
     */
    boolean existsById(int id);
}
