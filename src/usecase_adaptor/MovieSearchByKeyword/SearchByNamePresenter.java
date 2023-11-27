package usecase_adaptor.MovieSearchByKeyword;

import use_case.MovieSearchByKeyword.RecommendOutputData;
import use_case.MovieSearchByKeyword.SearchByNameOutputBoundary;
import usecase_adaptor.SearchList.SearchListViewModel;
import usecase_adaptor.ViewManagerModel;

public class SearchByNamePresenter implements SearchByNameOutputBoundary {
    private final SearchByNameViewModel searchByNameViewModel;

    private final MovieResultViewModel movieResultViewModel;
    private final SearchListViewModel searchListViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchByNamePresenter(ViewManagerModel viewManagerModel, SearchByNameViewModel searchByNameViewModel, MovieResultViewModel resultViewModel, SearchListViewModel searchListViewModel) {
        this.searchByNameViewModel = searchByNameViewModel;
        this.viewManagerModel = viewManagerModel;
        this.movieResultViewModel = resultViewModel;
        this.searchListViewModel = searchListViewModel;
    }

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

    @Override
    public void prepareFailView(String error) {
        searchByNameViewModel.setError(error);
        searchByNameViewModel.firePropertyChanged();

    }


//    @Override
//    public void SearchBykeywordSuccessView(RecommendOutputData recommendOutputData) {
//        SearchByNameState state = new SearchByNameState();
//        state.setMovies(recommendOutputData.getRecommendedMovies());
//        searchByNameViewModel.setState(state);
//    }
}