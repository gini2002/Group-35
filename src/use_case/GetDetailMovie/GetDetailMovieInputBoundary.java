package use_case.GetDetailMovie;
/**
 * The GetDetailMovieInputBoundary interface defines the input boundary for the get detail movie use case.
 * It declares the method for executing the getting detail movie based on the provided input data.
 */
public interface GetDetailMovieInputBoundary {
    /**
     * Executes and get detail of movie based on the provided input data.
     *
     * @param getDetailMovieInputData The input data when executing getting detail of certain movie.
     */
    void execute(GetDetailMovieInputData getDetailMovieInputData);
}