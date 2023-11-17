package usecase_adaptor.ShareWatchlist;

public class ShareWatchlistState {

    private String error = null;
    private String reseiverName = null;

    public ShareWatchlistState() {}

    public void setError(String error) {this.error = error;}

    public void setReceiverName(String name) {this.reseiverName = name;}

    public String getError() {return error;}

    public String getReseiverName() {return reseiverName;}
}
