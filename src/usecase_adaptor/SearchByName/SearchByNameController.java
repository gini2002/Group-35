package usecase_adaptor.SearchByName;

import entity.Movie;
import use_case.RecommendInputData;
import use_case.SearchByNameInteractor;

import java.util.List;

public class SearchByNameController {
    final SearchByNameInteractor searchByNameInteractor;

    public SearchByNameController(SearchByNameInteractor searchByNameInteractor){
        this.searchByNameInteractor = searchByNameInteractor;
    }

    public void execute(String keyword, List<Movie> searchHistory){
        RecommendInputData recommendInputData = new RecommendInputData(keyword, searchHistory);
        searchByNameInteractor.execute(recommendInputData);
    }
}