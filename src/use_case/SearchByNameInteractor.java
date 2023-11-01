package use_case;

import entity.Movie;

import java.util.List;

public class SearchByNameInteractor implements RecommendInputBoundary {
    final SearchByNameDataAccessInterface searchByNameDataAccessObject;
    final SearchByNameOutputBoundary searchByNamePresenter;

    public SearchByNameInteractor(SearchByNameDataAccessInterface searchByNameDataAccessObject,
                                  SearchByNameOutputBoundary searchByNamePresenter){
        this.searchByNameDataAccessObject = searchByNameDataAccessObject;
        this.searchByNamePresenter = searchByNamePresenter;
    }
    @Override
    public void execute(RecommendInputData recommendInputData) {
        String keyword = recommendInputData.getKeyword();
        List<Movie> moviesbykeyword = searchByNameDataAccessObject.searchByName(keyword);
        List<Movie> searchHistory = recommendInputData.getSearchHistory();
        searchByNameDataAccessObject.saveSearchHistory(searchHistory);
        RecommendOutputData recommendOutputData = new RecommendOutputData(searchHistory, moviesbykeyword);
        searchByNamePresenter.SearchBykeywordSuccessView(recommendOutputData);
    }
}

