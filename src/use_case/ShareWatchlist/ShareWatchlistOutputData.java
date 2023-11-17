package use_case.ShareWatchlist;

public class ShareWatchlistOutputData {

    private final String userName;

    public ShareWatchlistOutputData(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }
}
