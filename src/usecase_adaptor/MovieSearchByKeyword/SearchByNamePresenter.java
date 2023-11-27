package usecase_adaptor.MovieSearchByKeyword;

import use_case.MovieSearchByKeyword.RecommendOutputData;
import use_case.MovieSearchByKeyword.SearchByNameOutputBoundary;
import usecase_adaptor.SearchList.SearchListViewModel;
import usecase_adaptor.ViewManagerModel;

/**
 * The SearchByNamePresenter class acts as a presenter for the movie search by keyword use case.
 * It prepares the view models based on the output data and updates the view manager accordingly.
 */
public class SearchByNamePresenter implements SearchByNameOutputBoundary {
    /** The view model for the movie search by keyword. */
    private final SearchByNameViewModel searchByNameViewModel;

    /** The view model for displaying movie search results. */
    private final MovieResultViewModel movieResultViewModel;

    /** The view model for managing the search list. */
    private final SearchListViewModel searchListViewModel;

    /** The view manager model for controlling the active view. */
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a SearchByNamePresenter with the provided view models and view manager model.
     *
     * @param viewManagerModel       The view manager model for controlling the active view.
     * @param searchByNameViewModel  The view model for the movie search by keyword.
     * @param resultViewModel        The view model for displaying movie search results.
     * @param searchListViewModel    The view model for managing the search list.
     */
    public SearchByNamePresenter(ViewManagerModel viewManagerModel, SearchByNameViewModel searchByNameViewModel, MovieResultViewModel resultViewModel, SearchListViewModel searchListViewModel) {
        this.searchByNameViewModel = searchByNameViewModel;
        this.viewManagerModel = viewManagerModel;
        this.movieResultViewModel = resultViewModel;
        this.searchListViewModel = searchListViewModel;
    }

    /**
     * Prepares the success view based on the provided output data.
     *
     * @param outputData The output data containing information for the success view.
     */
    @Override
    public void prepareSuccessView(RecommendOutputData outputData) {
        searchByNameViewModel.setRecommendedMovies(outputData.getRecommendedMovies());
        searchByNameViewModel.firePropertyChanged();
        searchListViewModel.setSearchList(outputData.getSearchHistory());
        searchListViewModel.firePropertyChanged();
        movieResultViewModel.setRecommendedMovies(outputData.getRecommendedMovies());
        movieResultViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(searchByNameViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view with the given error message.
     *
     * @param error The error message to be included in the fail view.
     */
    @Override
    public void prepareFailView(String error) {
        searchByNameViewModel.setError(error);
        searchByNameViewModel.firePropertyChanged();
    }
}
