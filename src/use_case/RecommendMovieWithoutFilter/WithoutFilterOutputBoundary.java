package error_cases.RecommendMovieWithoutFilter;

public interface WithoutFilterOutputBoundary {
    void WithoutFilterSuccessView(WithoutFilterOutputData outputData);

    void WithoutFilterFailView(String error);
}
