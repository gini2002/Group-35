package use_case.GetDetailMovie;
import entity.Movie;
import entity.MovieFactory;
import java.util.ArrayList;

public class GetDetailMovieInteractor implements GetDetailMovieInputBoundary {
    final GetDetailMovieOutputBoundary getDetailMoviePresenter;
    final MovieFactory movieFactory;
    final GetDetailMovieDataAccessInterface getDetailMovieDataAccessObject;

    public GetDetailMovieInteractor(GetDetailMovieOutputBoundary getDetailMovieOutputBoundary,
                                    MovieFactory movieFactory,
                                    GetDetailMovieDataAccessInterface getDetailMovieDataAccessObject) {
        this.getDetailMoviePresenter = getDetailMovieOutputBoundary;
        this.getDetailMovieDataAccessObject = getDetailMovieDataAccessObject;
        this.movieFactory = movieFactory;
    }


    @Override
    public void execute(GetDetailMovieInputData getDetailMovieInputData) {
        String name = getDetailMovieInputData.getName();
        if (!getDetailMovieDataAccessObject.existsByName(name)) {
            getDetailMoviePresenter.getDetailMovieFailView("No Such Movie");
        }
        Movie movie = getDetailMovieDataAccessObject.getByName(name);
        String title = movie.getName();
        String overview = movie.getOverview();
        ArrayList<String> genre = movie.getGenre();
        String poster_path = movie.getPoster_path();
        GetDetailMovieOutputData getDetailMovieOutputData = new GetDetailMovieOutputData(title,
                overview, genre, poster_path);
        getDetailMoviePresenter.getDetailMovieSuccessView(getDetailMovieOutputData);
    }
}
