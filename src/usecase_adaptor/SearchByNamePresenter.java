package usecase_adaptor;

import use_case.RecommendOutputData;
import use_case.SearchByNameOutputBoundary;
import usecase_adaptor.ViewModel;

public class SearchByNamePresenter implements SearchByNameOutputBoundary {
    private final SearchByNameViewModel searchByNameViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchByNamePresenter(ViewManagerModel viewManagerModel, SearchByNameViewModel searchByNameViewModel) {
        this.searchByNameViewModel = searchByNameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RecommendOutputData outputData) {
        searchByNameViewModel.setRecommendedMovies(outputData.getRecommendedMovies());
        searchByNameViewModel.firePropertyChanged();
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
