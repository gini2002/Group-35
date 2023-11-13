package use_case.GetWatchList;

public class GetWatchListInputData {
    private final String name;
    private final int id;
    public GetWatchListInputData(String name, int id){
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }
}
