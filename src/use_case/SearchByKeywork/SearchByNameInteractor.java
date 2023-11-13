package use_case.SearchByKeywork;

import data_access.MovieDataAccessInterface;
import entity.Movie;

import java.util.List;

public class SearchByNameInteractor implements RecommendInputBoundary {
    final MovieDataAccessInterface searchByNameDataAccessObject;
    final SearchByNameOutputBoundary searchByNamePresenter;

    public SearchByNameInteractor(MovieDataAccessInterface searchByNameDataAccessObject,
                                  SearchByNameOutputBoundary searchByNamePresenter){
        this.searchByNameDataAccessObject = searchByNameDataAccessObject;
        this.searchByNamePresenter = searchByNamePresenter;
    }
    @Override
    public void execute(RecommendInputData recommendInputData) {
        String keyword = recommendInputData.getKeyword();
        List<Movie> moviesbykeyword = searchByNameDataAccessObject.searchMoviesByKeyword(keyword);
        List<Movie> searchHistory = recommendInputData.getSearchHistory();
        searchByNameDataAccessObject.addToSearchHistory(searchHistory);
        RecommendOutputData recommendOutputData = new RecommendOutputData(searchHistory, moviesbykeyword);
        searchByNamePresenter.SearchBykeywordSuccessView(recommendOutputData);
    }
}

