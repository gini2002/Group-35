package usecase_adaptor;

import use_case.RecommendOutputData;
import use_case.SearchByNameOutputBoundary;

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
