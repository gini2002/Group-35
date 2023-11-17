package use_case.GetDetailMovie;

public interface GetDetailMovieOutputBoundary {
    void getDetailMovieFailView(String error);

    void getDetailMovieSuccessView(GetDetailMovieOutputData response);
}
