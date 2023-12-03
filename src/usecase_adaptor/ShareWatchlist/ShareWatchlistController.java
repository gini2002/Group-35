package usecase_adaptor.ShareWatchlist;

import use_case.ShareWatchlist.ShareWatchlistInputBoundary;
import use_case.ShareWatchlist.ShareWatchlistInputData;

public class ShareWatchlistController {

    private final ShareWatchlistInputBoundary shareWatchlistInputBoundary;

    /**
     * initiate the controller.
     * @param shareWatchlistInputBoundary the input boundary.
     */
    public ShareWatchlistController(ShareWatchlistInputBoundary shareWatchlistInputBoundary) {
        this.shareWatchlistInputBoundary = shareWatchlistInputBoundary;
    }

    /**
     * share sender's watchlist to receiver's shared watchlist.
     * @param userName1 sender's userName.
     * @param userName2 receiver's userName.
     */
    public void execute(String userName1, String userName2) {
        ShareWatchlistInputData inputData = new ShareWatchlistInputData(userName1, userName2);
        System.out.println("shared controller: " + inputData.getReceiverName() + inputData.getSenderName());
        shareWatchlistInputBoundary.execute(inputData);
    }
}
