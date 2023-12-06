package use_case.GetDetailMovie;

import entity.Movie;
import entity.MovieFactory;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * The GetDetailMovieInteractor class represents the interactor for get detail of movie use case.
 * It implements the GetDetailMovieInputBoundary interface and is responsible for executing the use case logic.
 */
public class GetDetailMovieInteractor implements GetDetailMovieInputBoundary {
    /** The presenter that show the result of the use case. */
    final GetDetailMovieOutputBoundary getDetailMoviePresenter;
    /** The data access object get information we need. */
    final GetDetailMovieDataAccessInterface getDetailMovieDataAccessObject;

    /**
     * Constructs a GetDetailMovieInteractor with the provided data access object and presenter.
     *
     * @param getDetailMovieOutputBoundary The presenter that show the result of the use case.
     * @param getDetailMovieDataAccessObject The data access object get information we need.
     */
    public GetDetailMovieInteractor(GetDetailMovieOutputBoundary getDetailMovieOutputBoundary,
                                    GetDetailMovieDataAccessInterface getDetailMovieDataAccessObject) {
        this.getDetailMoviePresenter = getDetailMovieOutputBoundary;
        this.getDetailMovieDataAccessObject = getDetailMovieDataAccessObject;
    }

    /**
     * Executes get detail of movie use case based on the provided input data.
     *
     * @param getDetailMovieInputData The input data for the get detail of movie use case.
     */
    @Override
    public void execute(GetDetailMovieInputData getDetailMovieInputData) {
        int id = getDetailMovieInputData.getId();
        if (!getDetailMovieDataAccessObject.existsById(id)) {
            getDetailMoviePresenter.getDetailMovieFailView("The resource you requested could not be found.");
        }
        else {
            Movie movie = getDetailMovieDataAccessObject.getdetailMovie(id);
            String title = movie.getName();
            String overview = movie.getOverview();
            ArrayList<String> genre = movie.getGenre();
            String poster_path = movie.getPoster_path();
            LocalDate releaseDate = movie.getReleaseDate();
            String loggedinusername = getDetailMovieInputData.getLoggedinusername();
            GetDetailMovieOutputData getDetailMovieOutputData = new GetDetailMovieOutputData(title,
                    overview, genre, poster_path, id, loggedinusername, releaseDate);
            getDetailMoviePresenter.getDetailMovieSuccessView(getDetailMovieOutputData);
        }
    }
}
