package usecase_adaptor;

import entity.Movie;
import use_case.RecommendInputData;
import use_case.SearchByNameInteractor;
import use_case.RecommendInputBoundary;

import java.util.List;

public class SearchByNameController {
    final RecommendInputBoundary searchByNameInteractor;

    public SearchByNameController(RecommendInputBoundary searchByNameInteractor){
        this.searchByNameInteractor = searchByNameInteractor;
    }

    public void execute(String keyword){
        RecommendInputData recommendInputData = new RecommendInputData(keyword);
        searchByNameInteractor.execute(recommendInputData);
    }
}