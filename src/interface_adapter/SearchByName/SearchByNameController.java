package interface_adapter.SearchByName;

import entity.Movie;
import use_case.SearchByKeywork.RecommendInputData;
import use_case.SearchByKeywork.SearchByNameInteractor;

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