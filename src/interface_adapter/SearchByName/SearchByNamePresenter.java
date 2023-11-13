package interface_adapter.SearchByName;

import use_case.SearchByKeywork.RecommendOutputData;
import use_case.SearchByKeywork.SearchByNameOutputBoundary;

public class SearchByNamePresenter implements SearchByNameOutputBoundary {
    private final SearchByNameViewModel searchByNameViewModel;

    public SearchByNamePresenter(SearchByNameViewModel searchByNameViewModel) {
        this.searchByNameViewModel = searchByNameViewModel;
    }

    @Override
    public void SearchBykeywordSuccessView(RecommendOutputData recommendOutputData) {
        SearchByNameState state = new SearchByNameState();
        state.setMovies(recommendOutputData.getRecommendedMovies());
        searchByNameViewModel.setState(state);
    }
}
