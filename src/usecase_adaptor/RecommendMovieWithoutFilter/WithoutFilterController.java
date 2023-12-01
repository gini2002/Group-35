package usecase_adaptor.RecommendMovieWithoutFilter;

import use_case.RecommendMovieWithoutFilter.WithoutFilterInputBoundary;
import use_case.RecommendMovieWithoutFilter.WithoutFilterInputData;

public class WithoutFilterController {
    final WithoutFilterInputBoundary withoutFilterInteractor;

    public WithoutFilterController(WithoutFilterInputBoundary withoutFilterInteractor){
        this.withoutFilterInteractor = withoutFilterInteractor;
    }

    public void execute(String watchlistId){
        WithoutFilterInputData withoutFilterInputData = new WithoutFilterInputData(watchlistId);
        withoutFilterInteractor.execute(withoutFilterInputData);
    }
}
