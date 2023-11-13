package use_case;

import data_access.MovieDataAccessObject;
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
        // Check if the keyword is not null or empty
        if (keyword == null || keyword.isEmpty()) {
            searchByNamePresenter.prepareFailView("Invalid keyword");
            return;
        }

        // Retrieve recommended movies based on the keyword from the data access
        List<Movie> recommendedMovies = searchByNameDataAccessObject.getRecommendedMovies(keyword);

        if (recommendedMovies.isEmpty()) {
            searchByNamePresenter.prepareFailView("No movies found for the keyword: " + keyword);
        } else {
            // Prepare a success view with the list of recommended movies
            RecommendOutputData outputData = new RecommendOutputData(recommendedMovies);
            searchByNamePresenter.prepareSuccessView(outputData);
        }
    }
//    @Override
//    public void execute(RecommendInputData recommendInputData) {
//        String keyword = recommendInputData.getKeyword();
//        List<Movie> moviesbykeyword = MovieDataAccessObject.getRecommendedMovies(keyword);
////        List<Movie> searchHistory = recommendInputData.getSearchHistory();
////        searchByNameDataAccessObject.saveSearchHistory(searchHistory);
//        RecommendOutputData recommendOutputData = new RecommendOutputData(moviesbykeyword);
//        searchByNamePresenter.SearchBykeywordSuccessView(recommendOutputData);
//    }
}

