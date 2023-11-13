package interface_adapter.GetDetailOfMovie;

import use_case.GetDetailMovie.GetDetailMovieInputBoundary;
import use_case.GetDetailMovie.GetDetailMovieInputData;
import use_case.GetDetailMovie.GetDetailMovieInteractor;
import use_case.clear_users.ClearInputData;

public class GetDetailMovieController {
    final GetDetailMovieInputBoundary getDetailMovieInteractor;

    public GetDetailMovieController(GetDetailMovieInteractor getDetailMovieInteractor){
        this.getDetailMovieInteractor = getDetailMovieInteractor;
    }

    public void execute(String name){
        GetDetailMovieInputData getDetailMovieInputData = new GetDetailMovieInputData(name);
        getDetailMovieInteractor.execute(getDetailMovieInputData);
    }
}
