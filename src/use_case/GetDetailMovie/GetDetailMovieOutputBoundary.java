package use_case.GetDetailMovie;
/**
 * The GetDetailMovieOutputBoundary interface defines the output boundary for get detail of certain movie use case.
 * It declares methods for preparing success and failure views based on the output data.
 */
public interface GetDetailMovieOutputBoundary {

    /**
     * Prepares a fail view with the given error message.
     * @param error The error message to be included in the fail view.
     */
    void getDetailMovieFailView(String error);

    /**
     * Prepares a success view with the given output data.
     * @param response The output data containing information for the success view.
     */
    void getDetailMovieSuccessView(GetDetailMovieOutputData response);
}
