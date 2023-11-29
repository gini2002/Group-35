package usecase_adaptor.ShareWatchlist;

public class ShareWatchlistState {

    private String error = null;
    private String reseiverName = null;

    private String loggedUserName = null;

    public ShareWatchlistState() {}

    public void setError(String error) {this.error = error;}

    public void setReceiverName(String name) {this.reseiverName = name;}

    public void setLoggedUserName(String name) {this.loggedUserName = name;}

    public String getError() {return error;}

    public String getReseiverName() {return reseiverName;}

    public String getLoggedUserName() {return loggedUserName;}
}
