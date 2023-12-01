package use_case.ShareWatchlist;

public class ShareWatchlistInputData {
    private final String userName1;
    private final String userName2;

    /**
     * initiate input data.
     * @param name1 userName of the sender.
     * @param name2 userName of the receiver.
     */
    public ShareWatchlistInputData(String name1, String name2) {
        this.userName1 = name1;
        this.userName2 = name2;
    }

    /**
     *
     * @return senders' userName.
     */
    public String getSenderName() {
        return userName1;
    }

    /**
     *
     * @return receiver's userName.
     */
    public String getReceiverName() {
        return userName2;
    }
}
