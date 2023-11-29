package usecase_adaptor.GetDetailOfMovie;

import use_case.GetDetailMovie.GetDetailMovieInputBoundary;
import use_case.GetDetailMovie.GetDetailMovieInputData;
import use_case.GetDetailMovie.GetDetailMovieInteractor;

public class GetDetailMovieController {
    final GetDetailMovieInputBoundary getDetailMovieInteractor;

    public GetDetailMovieController(GetDetailMovieInteractor getDetailMovieInteractor){
        this.getDetailMovieInteractor = getDetailMovieInteractor;
    }

    public void execute(String name, int id, String logged_in_username){
        GetDetailMovieInputData getDetailMovieInputData = new GetDetailMovieInputData(name, id, logged_in_username);
        getDetailMovieInteractor.execute(getDetailMovieInputData);
    }
}
