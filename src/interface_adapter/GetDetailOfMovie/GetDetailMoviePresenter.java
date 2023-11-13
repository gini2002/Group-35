package interface_adapter.GetDetailOfMovie;

import entity.Movie;
import use_case.GetDetailMovie.GetDetailMovieOutputBoundary;
import use_case.GetDetailMovie.GetDetailMovieOutputData;

public class GetDetailMoviePresenter implements GetDetailMovieOutputBoundary {
    private final GetDetailMovieViewModel getDetailMovieViewModel;

    public GetDetailMoviePresenter(GetDetailMovieViewModel getDetailMovieViewModel) {
        this.getDetailMovieViewModel = getDetailMovieViewModel;
    }

    @Override
    public void getDetailMovieSuccessView(GetDetailMovieOutputData response) {
        Movie movie = response.getMovie();

    }

    @Override
    public void getDetailMovieFailView(String error) {

    }
}
