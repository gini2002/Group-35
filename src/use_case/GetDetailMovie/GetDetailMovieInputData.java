package use_case.GetDetailMovie;

public class GetDetailMovieInputData {
    private String title;
    private int id;
    private  String loggedinusername;


    public GetDetailMovieInputData(String title, int id, String loggedinusername) {
        this.title = title;
        this.id = id;
        this.loggedinusername = loggedinusername;
    }

    public String getName(){return title;}

    public int getId(){return id;}

    public String getLoggedinusername() {return loggedinusername;}
}
