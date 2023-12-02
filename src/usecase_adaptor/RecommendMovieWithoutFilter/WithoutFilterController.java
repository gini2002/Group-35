package usecase_adaptor.RecommendMovieWithoutFilter;

import use_case.RecommendMovieWithoutFilter.WithoutFilterInputBoundary;
import use_case.RecommendMovieWithoutFilter.WithoutFilterInputData;

public class WithoutFilterController {
    final WithoutFilterInputBoundary withoutFilterInteractor;

    public WithoutFilterController(WithoutFilterInputBoundary withoutFilterInteractor){
        this.withoutFilterInteractor = withoutFilterInteractor;
    }

    public void execute(String username){
        WithoutFilterInputData withoutFilterInputData = new WithoutFilterInputData(username);
        withoutFilterInteractor.execute(withoutFilterInputData);
    }
}
