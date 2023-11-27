package use_case.GetDetailMovie;

public class GetDetailMovieInputData {
    public String title;
    public int id;

    public GetDetailMovieInputData(String title) {
        this.title = title;
        this.id = id;
    }

    String getName(){return title;}

    int getId(){return id;}
}
