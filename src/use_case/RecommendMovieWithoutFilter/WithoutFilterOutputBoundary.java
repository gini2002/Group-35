package use_case.RecommendMovieWithoutFilter;

public interface WithoutFilterOutputBoundary {
    void WithoutFilterSuccessView(WithoutFilterOutputData outputData);

    void WithoutFilterFailView(String error);
}
