package usecase_adaptor.ShareWatchlist;

public class ShareWatchlistState {

    private String error = null;
    private String reseiverName = null;

    private String loggedUserName = null;

    /**
     * initiate the state.
     */
    public ShareWatchlistState() {}

    /**
     * save error message.
     * @param error string message of failed share.
     */
    public void setError(String error) {this.error = error;}

    /**
     *
     * @param name string username of the receiver.
     */
    public void setReceiverName(String name) {this.reseiverName = name;}

    /**
     *
     * @param name string username of the sender.
     */
    public void setLoggedUserName(String name) {this.loggedUserName = name;}

    /**
     *
     * @return the error message after failed shared.
     */
    public String getError() {return error;}

    /**
     *
     * @return the receiver's username.
     */
    public String getReseiverName() {return reseiverName;}

    /**
     *
     * @return the sender's username.
     */
    public String getLoggedUserName() {return loggedUserName;}
}
