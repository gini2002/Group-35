package use_case.ShareWatchlist;

public class ShareWatchlistInputData {
    private final String userName1;
    private final String userName2;

    public ShareWatchlistInputData(String name1, String name2) {
        this.userName1 = name1;
        this.userName2 = name2;
    }

    public String getSenderName() {
        return userName1;
    }

    public String getReceiverName() {
        return userName2;
    }
}
