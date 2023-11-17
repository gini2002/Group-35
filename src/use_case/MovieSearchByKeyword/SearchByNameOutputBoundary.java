package use_case.MovieSearchByKeyword;

public interface SearchByNameOutputBoundary {
    void prepareSuccessView(RecommendOutputData outputData);

//    void prepareSuccessView(SearchByNameOutputData outputData);

    void prepareFailView(String error);
}
