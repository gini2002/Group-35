package usecase_adaptor.GetDetailOfMovie;

import use_case.GetDetailMovie.GetDetailMovieOutputBoundary;
import use_case.GetDetailMovie.GetDetailMovieOutputData;
import usecase_adaptor.ViewManagerModel;

import java.util.List;

/**
 * The GetDetailMoviePresenter class acts as a presenter for get the detail of a movie.
 * It prepares the view models based on the output data and updates the view manager accordingly.
 */
public class GetDetailMoviePresenter implements GetDetailMovieOutputBoundary {
    /** The view model for get the detail of certain movie. */
    private final GetDetailMovieViewModel getDetailMovieViewModel;

    /** The view manager model for controlling the active view. */
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a GetDetailMoviePresenter with the provided view models and view manager model.
     * @param viewManagerModel             The view manager model for controlling the active view.
     * @param getDetailMovieViewModel      The view model for get the detail of certain movie.
     */
    public GetDetailMoviePresenter(GetDetailMovieViewModel getDetailMovieViewModel
            , ViewManagerModel viewManagerModel
    ) {
        this.getDetailMovieViewModel = getDetailMovieViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view based on the provided output data.
     * @param response The output data containing information for the success view.
     */
    @Override
    public void getDetailMovieSuccessView(GetDetailMovieOutputData response) {
        String title = response.getTitle();
        String overview = response.getOverview();
        List<String> genre = response.getGenre();
        String poster_path = response.getPoster_path();
        int id = response.getId();
        GetDetailMovieState getDetailMovieState = getDetailMovieViewModel.getGetDetailMovieState();
        getDetailMovieState.setOverview(overview);
        getDetailMovieState.setGenre(genre);
        getDetailMovieState.setTitle(title);
        getDetailMovieState.setPoster_path(poster_path);
        getDetailMovieState.setId(id);
        getDetailMovieState.setReleaseDate(response.getRelease_data());
        getDetailMovieState.setLoggedinusername(response.getLoggedinusername());
        getDetailMovieViewModel.setLoggedinusername(response.getLoggedinusername());
        getDetailMovieViewModel.setOverview(overview);
        getDetailMovieViewModel.setGenre(genre);
        getDetailMovieViewModel.setTitle(title);
        getDetailMovieViewModel.setId(id);
        getDetailMovieViewModel.setPoster_path(poster_path);
        getDetailMovieViewModel.setGetDetailMovieState(getDetailMovieState);
        getDetailMovieViewModel.setReleaseDate(response.getRelease_data());
        getDetailMovieViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(getDetailMovieViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view based on the provided output data.
     * @param error The error message shown in the fail view.
     */
    @Override
    public void getDetailMovieFailView(String error) {
        GetDetailMovieState getDetailMovieState = getDetailMovieViewModel.getGetDetailMovieState();
        getDetailMovieState.setError(error);
        getDetailMovieViewModel.firePropertyChanged();
    }
}