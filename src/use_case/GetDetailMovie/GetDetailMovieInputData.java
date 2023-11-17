package use_case.GetDetailMovie;

public class GetDetailMovieInputData {
    public String title;

    public GetDetailMovieInputData(String title) {
        this.title = title;
    }

    String getName(){return title;}
}
