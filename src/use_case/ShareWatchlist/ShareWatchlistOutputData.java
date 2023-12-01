package use_case.ShareWatchlist;

public class ShareWatchlistOutputData {

    private final String userName;

    /**
     * initiate a output data object.
     * @param name of receiver user's userName.
     */
    public ShareWatchlistOutputData(String name) {
        this.userName = name;
    }

    /**
     *
     * @return the name of receiver's user's userName.
     */
    public String getUserName() {
        return userName;
    }
}
