package usecase_adaptor.GetDetailOfMovie;

import use_case.GetDetailMovie.GetDetailMovieInputBoundary;
import use_case.GetDetailMovie.GetDetailMovieInputData;
import use_case.GetDetailMovie.GetDetailMovieInteractor;
/**
 * The GetDetailMovieController class serves as a controller for initiating the get detail of a certain movie use case.
 * It delegates the execution to the corresponding interactor.
 */
public class GetDetailMovieController {
    /** The interactor responsible for executing the get detail of a movie use case. */
    final GetDetailMovieInputBoundary getDetailMovieInteractor;

    /**
     * Constructs a GetDetailMovieController with the provided interactor.
     *
     * @param getDetailMovieInteractor The interactor for executing get detail of a certain movie use case.
     */
    public GetDetailMovieController(GetDetailMovieInputBoundary getDetailMovieInteractor){
        this.getDetailMovieInteractor = getDetailMovieInteractor;
    }

    /**
     * Initiates the execution of the get detail of a certain movie use case with the certain id.
     * And transfer the name of user that is looged in currently to enable other use case including get use case.
     *
     * @param id                 The movie id of movie we are getting detail for.
     * @param logged_in_username The name of the user that is currently logged in.
     */
    public void execute(int id, String logged_in_username){
        GetDetailMovieInputData getDetailMovieInputData = new GetDetailMovieInputData(id, logged_in_username);
        getDetailMovieInteractor.execute(getDetailMovieInputData);
    }
}
