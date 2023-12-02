package use_case.GetDetailMovie;

public class GetDetailMovieInputData {

    private int id;
    private  String loggedinusername;


    public GetDetailMovieInputData(int id, String loggedinusername) {

        this.id = id;
        this.loggedinusername = loggedinusername;
    }


    public int getId(){return id;}

    public String getLoggedinusername() {return loggedinusername;}
}
