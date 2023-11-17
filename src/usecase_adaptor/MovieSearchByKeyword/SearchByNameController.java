package usecase_adaptor.MovieSearchByKeyword;

import use_case.MovieSearchByKeyword.RecommendInputData;
import use_case.MovieSearchByKeyword.RecommendInputBoundary;

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