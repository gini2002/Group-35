package use_case;

public interface SearchByNameOutputBoundary {
    void prepareSuccessView(RecommendOutputData outputData);

    void prepareFailView(String error);
}
